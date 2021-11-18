import com.kafkaSummitEurope.*
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.*
import org.apache.kafka.streams.kstream.internals.suppress.StrictBufferConfigImpl
import org.apache.logging.log4j.kotlin.logger
import java.time.Duration

class StreamProcessor(properties: StreamProperties) {

    val streams: KafkaStreams
    private val serdeRawData: SpecificAvroSerde<SensorData>
    private val serdeSingleData: SpecificAvroSerde<SensorDataPerValue>
    private val serdePreAggregatedData: SpecificAvroSerde<SensorDataPreAggregation>
    private val serdeAggregatedData: SpecificAvroSerde<SensorDataAggregation>
    private val serdeAggregationKey: SpecificAvroSerde<SensorDataAggregationKey>

    init {

        val registryConfig = mutableMapOf<String, String>()
        registryConfig[AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = properties.configureProperties()
            .getProperty("schema.registry.url")

        serdeRawData = SpecificAvroSerde<SensorData>()
        serdeRawData.configure(registryConfig, false)

        serdeSingleData = SpecificAvroSerde<SensorDataPerValue>()
        serdeSingleData.configure(registryConfig, false)

        serdePreAggregatedData = SpecificAvroSerde<SensorDataPreAggregation>()
        serdePreAggregatedData.configure(registryConfig, false)

        serdeAggregatedData = SpecificAvroSerde<SensorDataAggregation>()
        serdeAggregatedData.configure(registryConfig, false)

        serdeAggregationKey = SpecificAvroSerde<SensorDataAggregationKey>()
        serdeAggregationKey.configure(registryConfig, false)

        streams = KafkaStreams(createTopology(), properties.configureProperties())
    }

    private fun createTopology(): Topology {

        val processor = StreamsBuilder()
        val logger = logger("Streams Processor")

        val s1: KStream<String, SensorDataPerValue> = processor
            .stream(
                "sensor-data-raw",
                Consumed.with(Serdes.String(), serdeRawData)
            )
            .mapValues { value -> convertTemperature(value) }   // Fahrenheit -> Celsius
            .flatMapValues { value -> splitDataPoints(value) }  // One event per data point


        val s2: KGroupedStream<String, SensorDataPerValue> = s1
            .selectKey { _, value -> value.getType() }
            .groupBy(
                { _, value -> value.getType() },
//                { _, value -> SensorDataAggregationKey(value.getSensorId(), value.getType()) },
                Grouped.with(Serdes.String(), serdeSingleData)
            )


        val s3: KStream<Windowed<String>, SensorDataAggregation> = s2
            .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(10)))
            .aggregate(
                { SensorDataPreAggregation(0.0, 0, "", "") },   // dummy initializer
                { _, value, aggregate -> aggregateEvents(value, aggregate) },
                Materialized.with(Serdes.String(), serdePreAggregatedData)
            )
            .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
            .toStream()
            .peek { _, value -> logger.info("$value") }
            .mapValues { value -> calculateAverage(value) }

        s3
            .to(
                "t2",
                Produced.with(WindowedSerdes.TimeWindowedSerde(), serdeAggregatedData)
            )

        return processor.build()
    }

    private fun convertTemperature(event: SensorData): SensorData {

        val convertedValue: List<value_record> = event.getValue().map {
            if (it.getType() == "temperature" && it.getUnit() == "Fahrenheit") {

                it.setValue(((it.getValue() - 32) / 1.8))
                it.setUnit("Celsius")
            }
            it
        }

        event.setValue(convertedValue)
        return event
    }

    private fun splitDataPoints(event: SensorData): List<SensorDataPerValue> {

        return event.getValue().map {
            SensorDataPerValue(
                event.getSensorId(),
                it.getType(),
                it.getValue(),
                it.getUnit(),
                event.getTimestamp()
            )
        }
    }

    private fun aggregateEvents(
        value: SensorDataPerValue,
        aggregate: SensorDataPreAggregation
    ): SensorDataPreAggregation {

        return SensorDataPreAggregation(
            aggregate.getSum() + value.getValue(),
            aggregate.getCount() + 1,
            value.getUnit(),
            value.getTimestamp()
        )
    }

    private fun calculateAverage(value: SensorDataPreAggregation): SensorDataAggregation {

        return SensorDataAggregation(
            value.getSum() / value.getCount(),
            value.getCount(),
            value.getUnit(),
            value.getTimestamp()
        )
    }
}