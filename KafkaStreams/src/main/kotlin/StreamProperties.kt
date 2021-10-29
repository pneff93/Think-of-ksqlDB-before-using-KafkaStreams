import org.apache.kafka.streams.StreamsConfig
import java.util.*

class StreamProperties {

    fun configureProperties(): Properties {

        val settings = Properties()
        settings.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        settings.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streamsId")
        settings.setProperty("schema.registry.url", "http://localhost:8081")

        return settings
    }
}