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
public class PasswordResetRequestConsumerConfig {

    @Autowired
    private ConsumersConfig consumersConfig;

    @Bean
    public ConsumerFactory<String, PasswordResetRequestMessage> passwordResetRequestKafkaConsumerFactory() {
        DefaultJackson2JavaTypeMapper jsonMapper = new DefaultJackson2JavaTypeMapper();
        jsonMapper.addTrustedPackages("*");

        JsonDeserializer<PasswordResetRequestMessage> jsonDeserializer = new JsonDeserializer<>(PasswordResetRequestMessage.class);
        jsonDeserializer.setTypeMapper(jsonMapper);

        return new DefaultKafkaConsumerFactory<>(consumersConfig.consumerConfigs(jsonDeserializer), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PasswordResetRequestMessage> passwordResetRequestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PasswordResetRequestMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(passwordResetRequestKafkaConsumerFactory());
        return factory;
    }
}
