# Think of ksqlDB before using Kafka Streams

[![LinkedIn][linkedin-shield]][linkedin-url]

A streaming data pipeline typically consists of data transformation, wrangling, and (time-based window) aggregation. On top of that, we must also guarantee data integrity. One might think of [Kafka Streams](https://kafka.apache.org/documentation/streams/) to solve all these challenges, and it is definitely a good choice. However, in many cases, [ksqlDB](https://ksqldb.io/) queries are simpler, faster to implement, and work fine.

This repository was used in a Confluent meetup. You can watch the recording in the [Community Forum](https://forum.confluent.io/t/recording-ready-to-view-speaker-q-a-thread-30-march-2022-think-of-using-ksqldb-before-using-kafka-streams/4450).

![](image.png)


## Run on Confluent for Kubernetes (CFK) with Minikube

### Prerequisites
* Helm
* Minikube
* Docker
* kubectl


### Environment
Start k8s cluster with: 
```shell
minikube start --cpus=6 --disk-size='100gb' --memory=8192
```

Follow the instructions (1 & 2) on [Quickstart](https://docs.confluent.io/operator/current/co-quickstart.html)
to create a `confluent` namespace and to install CFK.

Deploy Kafka components with:
```shell
kubectl apply -f ./confluent-platform.yaml
```

### Deployment

Build images:
```shell
docker build -t kafkaproducermeetup22:0.1.0 .
docker build -t kafkastreamsmeetup22:0.1.0 .
```

We need to load the images into the minikube cluster:
```shell
minikube image load kafkaproducermeetup22:0.1.0
minikube image load kafkastreamsmeetup22:0.1.0
```

Then we deploy images, e.g.:
```shell
kubectl apply -f ./KafkaProducer/Deployment.yaml
kubectl apply -f ./KafkaStreams/Deployment.yaml
```

### See the data


We do a port forwarding to the control center:
```shell
kubectl port-forward controlcenter-0 9021:9021 -n confluent
```
We can then see the data flow in the control center under:
```localhost:9021```. 


## Sources

### Schema Registry
Additional sources in order to work with Avro as a schema are:

* [Gradle Avro plugin](https://github.com/davidmc24/gradle-avro-plugin)
* [Kafka Streams Avro Serde](https://docs.confluent.io/platform/current/streams/developer-guide/datatypes.html)
* [ksqlDB Avro](https://docs.ksqldb.io/en/latest/reference/serialization/#avro)

### CFK
* [Quickstart](https://docs.confluent.io/operator/current/co-quickstart.html)
* [Deployment](https://stackoverflow.com/questions/42564058/how-to-use-local-docker-images-with-minikube)


[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/patrick-neff-7bb3b21a4/