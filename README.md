# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

![](image.png)


## Run in Confluent Cloud

We run the application in Confluent Cloud. Therefore, we need to provide additional
configurations for the clients. A good start of how to deploy services in CC can be found
[here](https://docs.confluent.io/cloud/current/client-apps/config-client.html).
Credentials are encrypted via [blackbox](https://github.com/StackExchange/blackbox#installation-instructions).

### Kafka Producer & Kafka Streams

We use [Gradle](https://gradle.org/) to build and run the applications:

```shell
./gradlew run
```

## Sources

### Schema Registry
Additional sources in order to work with Avro as a schema are:

* [Gradle Avro plugin](https://github.com/davidmc24/gradle-avro-plugin)
* [Kafka Streams Avro Serde](https://docs.confluent.io/platform/current/streams/developer-guide/datatypes.html)
* [ksqlDB Avro](https://docs.ksqldb.io/en/latest/reference/serialization/#avro)

### Metrics
* [Confluent Metrics Reporter](https://docs.confluent.io/platform/7.0.0/kafka/metrics-reporter.html#installation)
* [Kafka Monitoring and Metrics using JMX](https://docs.confluent.io/platform/current/installation/docker/operations/monitoring.html)
* [JMX Export ksqlDB](https://docs.ksqldb.io/en/latest/operate-and-deploy/monitoring/)

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/

### Confluent Cloud Config
* [Config](https://docs.confluent.io/cloud/current/client-apps/config-client.html)