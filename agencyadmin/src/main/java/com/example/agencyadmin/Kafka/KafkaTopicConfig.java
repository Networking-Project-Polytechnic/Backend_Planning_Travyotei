package com.example.agencyadmin.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String SCHEDULE_TOPIC = "schedule-updates";

    @Bean
    public NewTopic scheduleUpdatesTopic() {
        return TopicBuilder.name(SCHEDULE_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
