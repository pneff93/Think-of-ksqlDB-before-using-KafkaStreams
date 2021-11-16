import com.kafkaSummitEurope.SensorData
import com.kafkaSummitEurope.SensorDataPerValue
import com.kafkaSummitEurope.value_record
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Produced

class StreamProcessor(properties: StreamProperties) {

    val streams: KafkaStreams
    private val serdeRawData: SpecificAvroSerde<SensorData>
    private val serdeSingleData: SpecificAvroSerde<SensorDataPerValue>

    init {

        val registryConfig = mutableMapOf<String, String>()
        registryConfig[AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = properties.configureProperties()
            .getProperty("schema.registry.url")

        serdeRawData = SpecificAvroSerde<SensorData>()
        serdeRawData.configure(registryConfig, false)

        serdeSingleData = SpecificAvroSerde<SensorDataPerValue>()
        serdeSingleData.configure(registryConfig, false)

        streams = KafkaStreams(createTopology(), properties.configureProperties())
    }

    private fun createTopology(): Topology {

        val processor = StreamsBuilder()

        processor
            .stream(
                "sensor-data-raw",
                Consumed.with(Serdes.String(), serdeRawData)
            )
            .mapValues { value -> convertTemperature(value) } // Fahrenheit -> Celsius
            .flatMapValues { value -> splitDataPoints(value) } // One event per data point
//            .groupBy { key, value ->  }
//            .aggregate()
//            .windowed
//            .suppress
//            .toStream
            .to(
                "t2",
                Produced.with(Serdes.String(), serdeSingleData)
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
}