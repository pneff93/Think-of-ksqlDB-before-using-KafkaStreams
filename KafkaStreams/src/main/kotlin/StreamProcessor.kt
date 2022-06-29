import com.kafkaStreamsExample.*
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClientConfig
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.*
import org.apache.logging.log4j.kotlin.logger
import java.time.Duration
import kotlin.math.roundToInt

class StreamProcessor(properties: StreamProperties) {

    val streams: KafkaStreams
    private val serdeRawData: SpecificAvroSerde<SensorData>
    private val serdeSingleData: SpecificAvroSerde<SensorDataPerValue>
    private val serdePreAggregatedData: SpecificAvroSerde<SensorDataPreAggregation>
    private val serdeAggregatedData: SpecificAvroSerde<SensorDataAggregation>
    private val serdeAggregatedKey: SpecificAvroSerde<SensorDataAggregationKey>

    init {

        val registryConfig = mutableMapOf<String, String>()
        registryConfig[AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = properties.configureProperties()
            .getProperty("schema.registry.url")
        registryConfig[AbstractKafkaSchemaSerDeConfig.BASIC_AUTH_CREDENTIALS_SOURCE] = properties.configureProperties()
            .getProperty("basic.auth.credentials.source")
        registryConfig[SchemaRegistryClientConfig.USER_INFO_CONFIG] = properties.configureProperties()
            .getProperty("schema.registry.basic.auth.user.info")

        serdeRawData = SpecificAvroSerde<SensorData>()
        serdeRawData.configure(registryConfig, false)

        serdeSingleData = SpecificAvroSerde<SensorDataPerValue>()
        serdeSingleData.configure(registryConfig, false)

        serdePreAggregatedData = SpecificAvroSerde<SensorDataPreAggregation>()
        serdePreAggregatedData.configure(registryConfig, false)

        serdeAggregatedData = SpecificAvroSerde<SensorDataAggregation>()
        serdeAggregatedData.configure(registryConfig, false)

        serdeAggregatedKey = SpecificAvroSerde<SensorDataAggregationKey>()
        serdeAggregatedKey.configure(registryConfig, true) // true because it's a key

        streams = KafkaStreams(createTopology(), properties.configureProperties())
        logger("Kafka Streams").info(createTopology().describe())
    }

    private fun createTopology(): Topology {

        val processor = StreamsBuilder()
        val windowSizeInMillis = 10000L

        // Consume
        processor
            .stream(
                "sensor-data-raw",
                Consumed.with(Serdes.String(), serdeRawData)
            )

            // Transform
            .mapValues { value -> convertTemperature(value) }                               // Fahrenheit -> Celsius
            .flatMapValues { value -> splitDataPoints(value) }                              // One event per data point

            // Group by new key
            .groupBy(
                { _, value -> SensorDataAggregationKey(value.getSensorId(), value.getType()) },
                Grouped.with(serdeAggregatedKey, serdeSingleData)                           // -> repartition topic
            )

            // Aggregate over hopping window
            .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMillis(windowSizeInMillis)))
            .aggregate(
                { SensorDataPreAggregation(0.0, 0, "", "") },      // dummy initializer
                { _, value, aggregate -> aggregateEvents(value, aggregate) },                // calculate sum and count
                Materialized.with(serdeAggregatedKey, serdePreAggregatedData)                // -> changelog topic
            )
            .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
            .toStream()
            .mapValues { value -> calculateAverage(value) }                                 // calculate average

            // Produce
            .to(
                "sensor-data-aggregation-streams",
                Produced.with(
                    WindowedSerdes.TimeWindowedSerde(serdeAggregatedKey, windowSizeInMillis),   // key serde
                    serdeAggregatedData                                                         // value serde
                )
            )

        return processor.build()
    }

    // Fahrenheit -> Celsius
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

    // One data point -> list of data points
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

    // Aggregation (sum & count)
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

    // Calculate average out of sum and count
    private fun calculateAverage(value: SensorDataPreAggregation): SensorDataAggregation {
        return SensorDataAggregation(
            ((value.getSum() / value.getCount()) * 10.0).roundToInt() / 10.0,
            value.getCount(),
            value.getUnit(),
            value.getTimestamp()
        )
    }
}