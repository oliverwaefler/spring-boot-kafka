package com.example.kafka

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate


/**
 * https://crunchify.com/getting-java-net-unknownhostexception-nodename-nor-servname-provided-or-not-known-error-on-mac-os-x-update-your-privateetchosts-file/
 */
//@Configuration
class KafkaConfig {
//	@Value("\${kafka.group.id}")
//	private val groupId: String? = null
//
//	@Value("\${kafka.reply.topic}")
//	private val replyTopic: String? = null

	@Bean
	fun replyingKafkaTemplate(pf: ProducerFactory<String, MyData>,
							  cklcf: ConcurrentKafkaListenerContainerFactory<String, String>): ReplyingKafkaTemplate<String, MyData, String> {
		return ReplyingKafkaTemplate(pf, cklcf.createContainer("bla"))
	}

//	@Bean
//	fun myTemplate( kafkaTemplate: KafkaTemplate<String, String>): KafkaTemplate<String, String> {
//		return kafkaTemplate;
//	}

//	@Bean
//	fun replyTemplate(pf: ProducerFactory<String, Result>?,
//					  factory: ConcurrentKafkaListenerContainerFactory<String?, Result?>): KafkaTemplate<String, Result>? {
//		val kafkaTemplate: KafkaTemplate<String, Result> = KafkaTemplate<K, V>(pf)
//		factory.getContainerProperties().isMissingTopicsFatal = false
//		factory.setReplyTemplate(kafkaTemplate)
//		return kafkaTemplate
//	}

//	@Bean
//	fun kafkaTemplate(kafkaTemplate: KafkaTemplate<String, String>): KafkaTemplate<String, String>? {
////		val kafkaTemplate: KafkaTemplate<String, Result> = KafkaTemplate<K, V>(pf)
////		factory.getContainerProperties().isMissingTopicsFatal = false
////		factory.setReplyTemplate(kafkaTemplate)
//		return kafkaTemplate
//	}

//	@Bean
//	fun replyingTemplate(pf: ProducerFactory<String, String>,
//			repliesContainer: ConcurrentMessageListenerContainer<String, String>): ReplyingKafkaTemplate<String, String, String> {
//		return ReplyingKafkaTemplate(pf, repliesContainer)
//	}
}