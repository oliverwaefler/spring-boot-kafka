package com.example.kafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate


@SpringBootApplication
class KafkaApplication {

	@Bean
	fun replyingKafkaTemplate(pf: ProducerFactory<String, String>,
							  factory: ConcurrentKafkaListenerContainerFactory<String, String>): ReplyingKafkaTemplate<String, String, String> {
		val replyContainer: ConcurrentMessageListenerContainer<String, String> = factory.createContainer("replyTopic")
		replyContainer.containerProperties.isMissingTopicsFatal = false
		replyContainer.containerProperties.groupId = "group_rpc"
		return ReplyingKafkaTemplate(pf, replyContainer)
	}
}

fun main(args: Array<String>) {
	runApplication<KafkaApplication>(*args)
}
