import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.config.SaslConfigs
import org.apache.kafka.common.config.SslConfigs
import org.apache.kafka.streams.StreamsConfig
import java.util.*

class StreamProperties {

    fun configureProperties(): Properties {

        val settings = Properties()
        settings.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streamsId")
        settings.setProperty(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0")
        settings.setProperty("schema.registry.url", "http://localhost:8081")

        // SSL
//        settings.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:19093")
//        settings.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL")
//
//        settings.setProperty(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "confluent")
//        settings.setProperty(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "src/main/resources/kafka.client.truststore.jks")
//
//        settings.setProperty(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "src/main/resources/kafka.client.keystore.jks")
//        settings.setProperty(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "confluent")
//        settings.setProperty(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "confluent")


        // SASL
        settings.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:19094")

        settings.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL")
        settings.setProperty(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "confluent")
        settings.setProperty(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "src/main/resources/kafka.client.truststore.jks")

        settings.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN")
        settings.setProperty(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"client\" password=\"client-secret\";")

        return settings
    }
}