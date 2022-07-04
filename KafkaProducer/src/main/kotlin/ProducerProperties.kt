import org.apache.kafka.clients.producer.ProducerConfig
import java.util.*

class ProducerProperties {

    fun configureProperties() : Properties{

        val settings = Properties()
        settings.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        settings.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer")
        settings.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092")
        settings.setProperty("schema.registry.url", "http://schemaregistry.confluent.svc.cluster.local:8081")

        return settings
    }
}