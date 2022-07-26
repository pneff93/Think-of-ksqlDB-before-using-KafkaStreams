# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

Here, we focus on a secured environment using SSL and SASL_SSL.

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
* authentication
  * via SSL (mTLS)
  * via SASL Plain
* authorization via ACLs

We work with three hosts `PLAIN` (unsecured), `SSL` (secured), and `SASL_SSL` (secured); 
all have the same hostname but different ports.
* Inter Broker communication is using `SASL_SSL` (previous `SSL`)
* The Producer and Consumer clients are using `SSL` or `SASL_SSL` depending on the port
* Schema Registry and Control Center are using `PLAIN`

### SSL Data Encryption (TLS) & Authentication (mTLS, two-way authentication) -> SSL

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

### SASL Authentication and SSL Data Encryption -> SASL_SSL

Because we use `SASL` Plain username and passwords are available in plaintext.
Therefore, we just need a Java Authentication and Authorization Service (JAAS) file.

A detailed explanation about configuring SASL can be found [here](./SASL).

Once set up, we can either produce and consume messages via CLI:
```shell
kafka-console-producer  --broker-list broker-1:19094 --topic topic-1  --producer.config SASL/client.properties
```

```shell
kafka-console-consumer --bootstrap-server broker-1:19094 --topic topic-1 --consumer.config SASL/client.properties --from-beginning
```

### Run pipeline

To run the pipeline, choose either `SSL` or `SASL_SSL` by commenting 
the configuration. Then run:
```shell
./gradlew run
```


## Sources

Additional sources in order to work with Avro as a schema are:

* [Security Course](https://www.udemy.com/course/apache-kafka-security/)
* [SSL Key creation](https://mariadb.com/docs/security/data-in-transit-encryption/create-self-signed-certificates-keys-openssl/)
* [SSL Certificates creation](https://docs.confluent.io/platform/current/security/security_tutorial.html#configuring-host-name-verification)
* [SASL](https://docs.confluent.io/platform/current/kafka/authentication_sasl/authentication_sasl_plain.html#auth-sasl-plain-broker-config)

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/