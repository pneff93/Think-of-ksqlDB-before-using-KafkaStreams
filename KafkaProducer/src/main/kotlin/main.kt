import com.kafkaStreamsExample.SensorData
import kotlinx.coroutines.runBlocking

fun main() {

    val kafkaProducer = KafkaProducer()
    val properties = ProducerProperties()

    val sensorData = SensorData::class.java.getResource("/sensorData.txt").readText().split("\n")

    runBlocking {

        while (true) {

            val threadKafkaProducer = kafkaProducer.produceEvents(properties, sensorData)
            threadKafkaProducer.join()
        }
    }
}