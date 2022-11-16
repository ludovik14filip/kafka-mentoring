package org.epam.learn.config.topic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class TopicConfig {
    @Bean
    public KafkaAdmin.NewTopics topics456() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("taxi")
                        .replicas(2)
                        .partitions(4)
                        .build(),
                TopicBuilder.name("defaultPart")
                        .replicas(2)
                        .partitions(4)
                        .build());
    }
}
