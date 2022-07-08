# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

![](image.png)


## Run on Confluent Cloud

We run the application on Confluent Cloud (CC). Therefore, we need to provide additional
configurations for the clients. A good start of how to deploy services in CC can be found
[here](https://docs.confluent.io/cloud/current/client-apps/config-client.html).
Credentials are encrypted via [blackbox](https://github.com/StackExchange/blackbox#installation-instructions)
and need to be inserted in the corresponding property files.

### Kafka Producer & Kafka Streams

We use [Gradle](https://gradle.org/) to build and run the applications:

```shell
./gradlew run
```

## Metrics API

We can get metrics of the cluster (not clients) via the Metrics API. It provides two endpoints:
`/query` to get the metrics directly and `/export` which can be used by Prometheus.
[Here](https://docs.confluent.io/cloud/current/monitoring/metrics-api.html#prometheus) you can find a good starting point.

### `/query`

Use a cloud API key and execute to get the bytes being sent:

```shell
http 'https://api.telemetry.confluent.cloud/v2/metrics/cloud/query' --auth 'key:secret' < metrics_query.json
```
Make sure to have a valid `timestamp` and the correct `clusterId` in your query file.
You should receive something likes this:

````json
{
    "data": [
        {
            "metric.topic": "sensor-data-aggregation-streams",
            "timestamp": "2022-07-08T11:00:00Z",
            "value": 1188.0
        },
        {
            "metric.topic": "sensor-data-aggregation-streams",
            "timestamp": "2022-07-08T12:00:00Z",
            "value": 0.0
        },
        {
            "metric.topic": "sensor-data-raw",
            "timestamp": "2022-07-08T11:00:00Z",
            "value": 111384.0
        },
        {
            "metric.topic": "sensor-data-raw",
            "timestamp": "2022-07-08T12:00:00Z",
            "value": 19992.0
        },
        {
            "metric.topic": "streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-changelog",
            "timestamp": "2022-07-08T11:00:00Z",
            "value": 0.0
        },
        {
            "metric.topic": "streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-changelog",
            "timestamp": "2022-07-08T12:00:00Z",
            "value": 0.0
        },
        {
            "metric.topic": "streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-repartition",
            "timestamp": "2022-07-08T11:00:00Z",
            "value": 185855.0
        },
        {
            "metric.topic": "streamsId-KSTREAM-AGGREGATE-STATE-STORE-0000000004-repartition",
            "timestamp": "2022-07-08T12:00:00Z",
            "value": 38400.0
        },
        {
            "metric.topic": "streamsId-KTABLE-SUPPRESS-STATE-STORE-0000000010-changelog",
            "timestamp": "2022-07-08T11:00:00Z",
            "value": 25751.0
        },
        {
            "metric.topic": "streamsId-KTABLE-SUPPRESS-STATE-STORE-0000000010-changelog",
            "timestamp": "2022-07-08T12:00:00Z",
            "value": 0.0
        }
    ]
}
````


## Sources

### Schema Registry
Additional sources in order to work with Avro as a schema are:

* [Gradle Avro plugin](https://github.com/davidmc24/gradle-avro-plugin)
* [Kafka Streams Avro Serde](https://docs.confluent.io/platform/current/streams/developer-guide/datatypes.html)
* [ksqlDB Avro](https://docs.ksqldb.io/en/latest/reference/serialization/#avro)

### Confluent Cloud Config
* [Config](https://docs.confluent.io/cloud/current/client-apps/config-client.html)

### Metrics API
* [Metrics Overview](https://docs.confluent.io/cloud/current/client-apps/monitoring.html)
* [Examples](https://docs.confluent.io/cloud/current/monitoring/metrics-api.html#query-for-bytes-sent-to-consumers-per-minute-grouped-by-topic)

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/

