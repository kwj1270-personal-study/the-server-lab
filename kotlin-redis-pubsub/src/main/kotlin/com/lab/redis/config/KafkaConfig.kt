package com.lab.redis.config

import com.lab.redis.pojo.ChatMessage
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig(

    @Value("\${spring.kafka.bootstrap-servers[0]}")
    private val bootstrapServers: String,

    @Value("\${spring.kafka.consumer.group-id}")
    private val groupId: String,

    @Value("\${spring.kafka.consumer.auto-offset-reset}")
    private val offsetReset: String
) {

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, ChatMessage> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, ChatMessage> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    @Bean
    fun producerConfig(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        props[JsonSerializer.ADD_TYPE_INFO_HEADERS] = false
        return props
    }


    @Bean(name = ["pushConsumer"])
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ChatMessage>> {
        val consumerFactory = DefaultKafkaConsumerFactory(
            consumerConfigs(),
            StringDeserializer(),
            ErrorHandlingDeserializer(JsonDeserializer(ChatMessage::class.java))
        )
        val factory = ConcurrentKafkaListenerContainerFactory<String, ChatMessage>()
        factory.consumerFactory = consumerFactory
        return factory
    }

    private fun consumerConfigs(): Map<String, Any> {
        val configs: HashMap<String, Any> = HashMap()
        configs[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configs[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        configs[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = offsetReset
        return configs
    }
}
