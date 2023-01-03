# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

**In the meanwhile this repo became a playground for different ways of
deployment as well as exploring features such as 
Cluster Linking, or enabling metrics. You can find them under different branches. Currently,
this pipeline can be deployed:**

* [locally with Docker](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/local)
  * with JMX metrics
* [locally secured with Docker](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/local_security)
  * SSL & SASL_SSL
* [locally setting up Health+](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/local_health+)
* [with Confluent for Kubernetes using Minikube](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/cfk_minikube)
* [on Confluent Cloud](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/ccloud)
  * with Cluster Linking and Schema Linking
  * with RBAC
  * exporting Metrics to Grafana Cloud
  * exploring Audit Logs
* [on Confluent Cloud with Stream Designer](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/ccloud_stream_designer)
* [hybrid (locally but using Cluster Linking to transfer data to CC)](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/hybrid)

![](image.png)



[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/
