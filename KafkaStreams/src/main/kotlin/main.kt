import org.apache.logging.log4j.kotlin.logger

fun main() {

    val logger = logger("Kafka Streams")

    val streamProperties = StreamProperties()
    val streamProcessor = StreamProcessor(streamProperties)
    streamProcessor.streams.start()
    logger.info("Processor started")
}