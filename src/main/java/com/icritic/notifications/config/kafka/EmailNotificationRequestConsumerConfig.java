package com.icritic.notifications.config.kafka;

import com.icritic.notifications.dataprovider.kafka.entity.EmailNotificationRequestMessage;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class EmailNotificationRequestConsumerConfig {

    @Autowired
    private ConsumersConfig consumersConfig;

    @Bean
    public ConsumerFactory<String, EmailNotificationRequestMessage> emailNotificationRequestKafkaConsumerFactory() {
        DefaultJackson2JavaTypeMapper jsonMapper = new DefaultJackson2JavaTypeMapper();
        jsonMapper.addTrustedPackages("*");

        JsonDeserializer<EmailNotificationRequestMessage> jsonDeserializer = new JsonDeserializer<>(EmailNotificationRequestMessage.class);
        jsonDeserializer.setTypeMapper(jsonMapper);

        return new DefaultKafkaConsumerFactory<>(consumersConfig.consumerConfigs(jsonDeserializer), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailNotificationRequestMessage> emailNotificationRequestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailNotificationRequestMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(emailNotificationRequestKafkaConsumerFactory());
        return factory;
    }
}
