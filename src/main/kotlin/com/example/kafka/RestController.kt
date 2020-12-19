package com.example.kafka

import com.beust.klaxon.Klaxon
import mu.KotlinLogging
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController(
		val kafkaTemplate: ReplyingKafkaTemplate<String,String,  String>
) {
	companion object {
		private val logger = KotlinLogging.logger {}
	}

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], value = ["/queue"])
	fun postQueue(@RequestBody myData: MyData) {
		val topicName = "topic_job_queue"
		logger.debug { "Sending $myData to topic - $topicName" }
		kafkaTemplate.send(topicName, Klaxon().toJsonString(myData))
		logger.info { "Sent $myData to topic - $topicName" }
	}

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], value = ["/topic"])
	fun postTopic(@RequestBody myData: MyData) {
		val topicName = "topic_pubsub"
		logger.debug { "Sending $myData to topic - $topicName" }
		kafkaTemplate.send(topicName, Klaxon().toJsonString(myData))
		logger.info { "Sent $myData to topic - $topicName" }
	}

	@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], value = ["/rpc"])
	fun postRPC(@RequestBody myData: MyData) {
		val topicName = "topic_rpc"
		logger.debug { "Sending $myData to topic - $topicName" }
		val request = ProducerRecord<String, String>(topicName, Klaxon().toJsonString(myData))
		val response = kafkaTemplate.sendAndReceive(request)
		logger.info { "Sent $myData to topic - $topicName response is: ${response.get()}" }
	}
}