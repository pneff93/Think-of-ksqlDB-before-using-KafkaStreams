# Think of ksqlDB before using Kafka Streams - SSL

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

Here, we focus on a secured environment using SSL.

## Run locally with Docker

Start entire Kafka environment with:
```shell
docker-compose up -d
```
We can then see the data flow in the Control Center under:
```localhost:9021```.

## Set up Security

We set up security step by step consisting of: 
* data encryption via SSL
* authentication via SSL (mTLS)
* authorization via ACLs

We skip to implement SASL as authentication.

We work with two hosts `PLAIN` (unsecured) and `SSL` (secured); both have
the same hostname but different ports.
* Inter Broker communication is using `SSL`
* the Producer and Consumer clients are using `SSL`
* Schema Registry and Control Center are using the `PLAIN`

### SSL Data Encryption (TLS) & Authentication (mTLS, two-way authentication)

Overall, we need to 
1. create Certificate Authority, Key Store and Trust Store
2. set up SSL on the Kafka Brokers
3. set up SSL on the Kafka Clients

A detailed explanation about configuring SSL can be found [here](./SSL).
Once set up, we can either produce and consume messages via CLI:
```shell
kafka-console-producer  --broker-list broker-1:19093 --topic topic-1  --producer.config SSL/client-creds/client.properties
```
```shell
kafka-console-consumer --bootstrap-server broker-1:19093 --topic topic-1 --from-beginning --consumer.config SSL/client-creds/client.properties
```
Or we run our pipeline:
```shell
./gradlew run
```


## Sources

Additional sources in order to work with Avro as a schema are:

* [Security Course](https://www.udemy.com/course/apache-kafka-security/)
* [SSL Key creation](https://mariadb.com/docs/security/data-in-transit-encryption/create-self-signed-certificates-keys-openssl/)
* [SSL Certificates creation](https://docs.confluent.io/platform/current/security/security_tutorial.html#configuring-host-name-verification)

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/