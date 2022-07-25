import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.common.config.SslConfigs
import org.apache.kafka.streams.StreamsConfig
import java.util.*

class StreamProperties {

    fun configureProperties(): Properties {

        val settings = Properties()
        settings.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:19093")
        settings.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streamsId")
        settings.setProperty(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0")
        settings.setProperty("schema.registry.url", "http://localhost:8081")

        settings.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL")
        settings.setProperty(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "confluent")
        settings.setProperty(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "src/main/resources/kafka.client.truststore.jks")

        settings.setProperty(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "src/main/resources/kafka.client.keystore.jks")
        settings.setProperty(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "confluent")
        settings.setProperty(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "confluent")

        return settings
    }
}