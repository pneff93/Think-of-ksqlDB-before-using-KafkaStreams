import com.kafkaSummitEurope.SensorData
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.Produced

class StreamProcessor(properties: StreamProperties) {

    var streams : KafkaStreams
    private val serde: SpecificAvroSerde<SensorData>

    init {


//        val config = mapOf(
//            AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to properties.configureProperties()
//                .getProperty("schema.registry.url")
//        )
        val registryConfig = mutableMapOf<String, String>()
        registryConfig[AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG] = properties.configureProperties()
            .getProperty("schema.registry.url")
        serde = SpecificAvroSerde<SensorData>()
        serde.configure(registryConfig, false)

        streams = KafkaStreams(createTopology(), properties.configureProperties())
    }

    fun createTopology(): Topology {

        val processor = StreamsBuilder()

        processor
            .stream(
                "sensor-data-raw",
                Consumed.with(Serdes.String(), serde)
            )
//            .mapValues { value: SensorData ->
//                val r: List<Unit> = value.setValue() value.getValue().map {
//                    if (it.getType() == "temperature") {
//                        it.setUnit("Celsius")
//                    }
//                }
//            }
            .to(
                "tttttt",
                Produced.with(Serdes.String(), serde)
            )

        return processor.build()
    }
}