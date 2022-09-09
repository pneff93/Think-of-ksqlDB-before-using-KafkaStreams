import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.config.SaslConfigs
import java.util.*

class ProducerProperties {

    fun configureProperties() : Properties{

        val settings = Properties()
        settings.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        settings.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer")

        // Confluent Cloud Auth Broker
        settings.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "<Broker Endpoint>")
        settings.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL")
        settings.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN")

        val key = "<Key>"
        val secret = "<Secret>"
        settings.setProperty(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username='$key' password='$secret';")

        // Confluent Cloud Auth Schema Registry
        settings.setProperty("schema.registry.url", "<Schema Registry Endpoint>")
        settings.setProperty("basic.auth.credentials.source", "USER_INFO")
        settings.setProperty("schema.registry.basic.auth.user.info", "<Key>:<Secret>")
        settings[AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS] = false

        return settings
    }
}