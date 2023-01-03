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

### Kafka Streams

We use [Gradle](https://gradle.org/) to build and run the Kafka Streams application:

```shell
./gradlew run
```

### ksqlDB

In order to execute all statements, we need to open the ksqlDB client with:

```shell
docker exec -it ksqldb-cli ksql http://ksqldb-server:8088
```

## Metrics

_Note: Metrics have been added to this repository after the Meetup.
For a great repository containing metrics as well as the visualization with
Grafana, I refer to [kafka-platform-prometheus](https://github.com/jeanlouisboudart/kafka-platform-prometheus)._

In order, to see some insight metrics we distinguish between Confluent Metrics Reporter and
JMX (Java Management Extensions).

### Confluent Metrics Reporter

Simply add parameters to the environment of the broker.

```shell
KAFKA_METRIC_REPORTERS: io.confluent.metrics.reporter.ConfluentMetricsReporter
CONFLUENT_METRICS_REPORTER_BOOTSTRAP_SERVERS: broker:29092
CONFLUENT_METRICS_REPORTER_TOPIC_REPLICAS: 1
```
We then can see a dashboard in the control-center (`localhost:9021`):

![](./Metrics/control-center.png)


### JMX

We can export metrics from the broker and/or ksqlDB by adjusting related parameters in the
docker-compose file. Open `jconsole` and see those metrics.
With the JMX-exporter, we export desired metrics so that we finally can scrape them with
Prometheus. A possible extension might be using Grafana to visualize the metrics in a 
dashboard. However, we focussed here more on only exposing rather than using.

We can either monitor all metrics under `localhost:5556` from the JXM-exporter
or in Prometheus under `localhost:9090`.

I used the code from the [streamthoughts GitHub repository](https://github.com/streamthoughts/kafka-monitoring-stack-docker-compose/tree/master/etc/jmx_exporter)
for exporting the metrics.

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
