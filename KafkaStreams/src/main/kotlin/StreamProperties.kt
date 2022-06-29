import org.apache.kafka.common.config.SaslConfigs
import org.apache.kafka.streams.StreamsConfig
import java.util.*


class StreamProperties {

    fun configureProperties(): Properties {

        val settings = Properties()
        settings.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streamsId")
        settings.setProperty(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0")

        // Confluent Cloud Auth Broker
        settings.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "<Broker Endpoint>")
        settings.setProperty(StreamsConfig.SECURITY_PROTOCOL_CONFIG, "SASL_SSL")
        settings.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN")

        val key = "<Key>"
        val secret = "<Secret>"
        settings.setProperty(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username='$key' password='$secret';")

        // Confluent Cloud Auth Schema Registry
        settings.setProperty("schema.registry.url", "<Schema Registry Endpoint>")
        settings.setProperty("basic.auth.credentials.source", "USER_INFO")
        settings.setProperty("schema.registry.basic.auth.user.info", "<Key>:<Secret>")

        return settings
    }
}