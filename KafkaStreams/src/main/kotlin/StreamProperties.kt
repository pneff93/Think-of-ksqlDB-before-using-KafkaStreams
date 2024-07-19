import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.streams.StreamsConfig
import java.util.*

class StreamProperties {

    fun configureProperties(): Properties {

        val settings = Properties()
        settings.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:29092")
        settings.setProperty("schema.registry.url", "http://schema-registry:8081")
        settings.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "streamsId")
        settings.setProperty(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0")
        settings.setProperty(StreamsConfig.METRICS_RECORDING_LEVEL_CONFIG, "DEBUG")

//        settings[StreamsConfig.PRODUCER_PREFIX + ProducerConfig.INTERCEPTOR_CLASSES_CONFIG] =
//            "io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor"
//        settings[StreamsConfig.MAIN_CONSUMER_PREFIX + ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG] =
//            "io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor"

        return settings
    }
}