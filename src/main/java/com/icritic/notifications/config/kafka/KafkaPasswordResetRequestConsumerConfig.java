package com.icritic.notifications.config.kafka;

import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetRequestMessage;
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
public class KafkaPasswordResetRequestConsumerConfig {

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Bean
    public ConsumerFactory<String, PasswordResetRequestMessage> passwordResetRequestKafkaConsumerFactory() {
        DefaultJackson2JavaTypeMapper jsonMapper = new DefaultJackson2JavaTypeMapper();
        jsonMapper.addTrustedPackages("*");

        JsonDeserializer jsonDeserializer = new JsonDeserializer<>(PasswordResetRequestMessage.class);
        jsonDeserializer.setTypeMapper(jsonMapper);

        return new DefaultKafkaConsumerFactory<>(kafkaConsumerConfig.consumerConfigs(jsonDeserializer), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PasswordResetRequestMessage> kafkaPasswordResetRequestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PasswordResetRequestMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(passwordResetRequestKafkaConsumerFactory());
        return factory;
    }
}
