package com.example.kafka

import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.messaging.handler.annotation.SendTo
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
	fun consumeQueue1(message: String) {
		logger.info { "consumeQueue1: $message" }
	}

	@KafkaListener(
			topicPartitions = [TopicPartition(topic = "topic_job_queue", partitions = ["1"])],
			groupId = "g1")
	fun consumeQueue2(message: String) {
		logger.info { "consumeQueue2: $message" }
	}

	@KafkaListener(topics = ["topic_pubsub"], groupId = "p1")
	fun consumeTopic1(message: String) {
		logger.info { "consumeTopic1: $message" }
	}

	@KafkaListener(topics = ["topic_pubsub"], groupId = "p2")
	fun consumeTopic2(message: String) {
		logger.info { "consumeTopic2: $message" }
	}

	@KafkaListener(topics = ["topic_rpc"], groupId = "group_rpc")
	@SendTo
	fun consumeRPC(message: String): String {
		logger.info { "consumeRPC: $message" }
		return Date().toString()
	}

}