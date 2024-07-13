# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

> [!NOTE]
> In the meanwhile this repo became a playground for different ways of
> deployment as well as exploring features such as 
> Cluster Linking, or enabling metrics. You can find them under different branches.

| **Branch**                                                                                                                                            | **Additional Features**                                                                                                                                                            |
|-------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [local](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/local)                                                | with Metrics (C3, JMX)                                                                                                                                                                  |
| [local_security](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/local_security)                               | with Encryption: SSL,  Authentication: SASL and mTLS,  Authorization: ACL                                                                                                                   |
| [local_health+](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/local_health+)                                 | with Health+ (including CC Metrics API)                                                                                                                                                 |
| [local_c3_reduced](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/local_c3_reduced)       | with C3 in reduced infrastructure mode                                                                                                                                                  |
| [cfk_minikube](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/cfk_minikube)                | deployed with CFK (including Health+)                                                                                                                                              |
| [ccloud](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/ccloud)                                                | deployed in CC, testing Metrics API (/query and /export into Grafana Cloud), Audit Logs consuming, Cluster Linking via UI (CC to CC), Schema Linking via CLI (CC to CC), RBAC via Confluent CLI |
| [ccloud_stream_designer](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/ccloud_stream_designer)           | deployed in CC using Stream Designer to deploy the pipeline                                                                                                                                 |
| [hybrid](https://github.com/pneff93/Think-of-ksqlDB-before-using-KafkaStreams/tree/hybrid) | deployed locally but mirroring all topics to CC via Cluster Linking                                                                                                                           |


![](image.png)



[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/
