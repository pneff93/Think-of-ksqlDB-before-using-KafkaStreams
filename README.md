# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

![](image.png)


## Run locally with Docker

Start entire Kafka environment with:
```shell
docker-compose up -d
```
We can then see the data flow in the control center under:
```localhost:9021```. 

### Kafka Producer & Kafka Streams

We use [Gradle](https://gradle.org/) to build and run the Kafka Producer and Streams application:

```shell
./gradlew run
```

### ksqlDB

In order to execute all statements, we need to open the ksqlDB client with:

```shell
docker exec -it ksqldb-cli ksql http://ksqldb-server:8088
```

## Set up Health+

We can set up Health+ by adding these properties to the Kafka components:
````yaml
KAFKA_METRIC_REPORTERS: io.confluent.telemetry.reporter.TelemetryReporter
KAFKA_CONFLUENT_TELEMETRY_ENABLED: 'true'
KAFKA_CONFLUENT_TELEMETRY_API_KEY: <CLOUD API KEY>
KAFKA_CONFLUENT_TELEMETRY_API_SECRET: <CLOUD API SECRET>
````

### Metrics API

You can also scrape the CP metrics using the CC Metrics API (Open Preview), see the [documentation](https://api.telemetry.confluent.cloud/docs#section/Object-Model/Datasets).
Just request the endpoint `https://api.telemetry.confluent.cloud/v2/metrics/health-plus/query` and follow
the [Metrics API documentation](https://docs.confluent.io/cloud/current/monitoring/metrics-api.html#example-queries).



## Sources

* [Health+](https://docs.confluent.io/platform/current/health-plus/enable-health-plus.html)


[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/
