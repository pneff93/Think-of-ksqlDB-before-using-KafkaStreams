# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can see the recording here once available.

![](image.png)


## Run

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

## Sources

Additional sources in order to work with Avro as a schema are:

* [Gradle Avro plugin](https://github.com/davidmc24/gradle-avro-plugin)
* [Kafka Streams Avro Serde](https://docs.confluent.io/platform/current/streams/developer-guide/datatypes.html)
* [ksqlDB Avro](https://docs.ksqldb.io/en/latest/reference/serialization/#avro)

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/