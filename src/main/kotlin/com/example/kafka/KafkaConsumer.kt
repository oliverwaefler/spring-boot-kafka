package com.example.kafka

import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.stereotype.Component
import java.util.*


@Component
class KafkaConsumer {
	companion object {
		private val logger = KotlinLogging.logger {}
	}

	@KafkaListener(
			topicPartitions = [TopicPartition(topic = "topic_job_queue", partitions = ["0"])],
			groupId = "g1")
	fun consumeJobQueueKafka1(message: String) {
		logger.info { "consumeJobQueueKafka1: $message" }
	}

	@KafkaListener(
			topicPartitions = [TopicPartition(topic = "topic_job_queue", partitions = ["1"])],
			groupId = "g1")
	fun consumeJobQueueKafka2(message: String) {
		logger.info { "consumeJobQueueKafka2: $message" }
	}

	@KafkaListener(topics = ["topic_pubsub"], groupId = "p1")
	fun consumePubSubKafka1(message: String) {
		logger.info { "consumePubSubKafka1: $message" }
	}

	@KafkaListener(topics = ["topic_pubsub"], groupId = "p2")
	fun consumePubSubKafka2(message: String) {
		logger.info { "consumePubSubKafka2: $message" }
	}

	@KafkaListener(topics = ["topic_rpc"], groupId = "group_rpc")
	fun consumeRPC(message: String): String {
		logger.info { "consumeRPC: $message" }
		return Date().toString()
	}

}