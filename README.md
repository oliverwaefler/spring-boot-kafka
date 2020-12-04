# spring-boot-kafka

## Description

Simple spring boot application to interact with an Apache Kafka message broker using annotations.

## Getting Started

### Installing

```
docker pull spotify/kafka
docker run -p 2181:2181 -p 9092:9092 --name kafka --rm spotify/kafka
docker exec -it kafka /bin/bash
cd /opt/kafka_2.11-0.10.1.0/
-- create topic mit 2 partitions
./bin/kafka-topics.sh --zookeeper localhost:2181 --replication-factor 1 --create --topic topic_job_queue --partitions 2
./bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic topic_job_queue --partitions 2
```

## Authors

Oliver W. - email: oliverwaefler@gmail.com
