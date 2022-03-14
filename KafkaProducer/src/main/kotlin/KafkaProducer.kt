import com.google.gson.Gson
import com.kafkaStreamsExample.SensorData
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.logging.log4j.kotlin.logger
import java.text.SimpleDateFormat
import java.util.*

class KafkaProducer {

    private val logger = logger(javaClass.name)
    private val gson = Gson()

    fun produceEvents(properties: ProducerProperties, data: List<String>): Thread {

        val thread = Thread {

            val kafkaProducer = KafkaProducer<String, SensorData>(properties.configureProperties())

            Thread.sleep(10000)
            logger.info("Kafka Producer started")

            data.forEach { event ->

                val sensorData = gson.fromJson(event, SensorData::class.java)
                sensorData.setTimestamp(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'").format(Date()))

                kafkaProducer.send(ProducerRecord("sensor-data-raw", sensorData.getSensorId(), sensorData))
                logger.info("Sensor data produced with value: $sensorData")
                Thread.sleep(2000)
            }
        }

        thread.start()
        return thread
    }
}