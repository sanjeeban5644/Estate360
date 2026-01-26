package com.sanjeeban.CoreApartmentService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.BeanProperty;

@Configuration
public class KafkaConfig {

    @Value("${kakfa.topic.user-random_topic}")
    private String KAFKA_TOPIC_NAME;

    @Value("${kakfa.topic.email-topic}")
    private String EMAIL_KAFKA_TOPIC;

    @Bean
    public NewTopic userRandomTopic(){
        return new NewTopic("KAFKA_TOPIC_NAME",3,(short) 1);
    }

    @Bean
    public NewTopic emailTopic(){
        return new NewTopic("EMAIL_KAFKA_TOPIC",3,(short) 1);
    }
}
