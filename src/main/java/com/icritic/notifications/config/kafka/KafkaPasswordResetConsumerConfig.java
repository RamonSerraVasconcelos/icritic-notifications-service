package com.icritic.notifications.config.kafka;

import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetMessage;
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
public class KafkaPasswordResetConsumerConfig {

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Bean
    public ConsumerFactory<String, PasswordResetMessage> passwordResetKafkaConsumerFactory() {
        DefaultJackson2JavaTypeMapper jsonMapper = new DefaultJackson2JavaTypeMapper();
        jsonMapper.addTrustedPackages("*");

        JsonDeserializer jsonDeserializer = new JsonDeserializer<>(PasswordResetMessage.class);
        jsonDeserializer.setTypeMapper(jsonMapper);

        return new DefaultKafkaConsumerFactory<>( kafkaConsumerConfig.consumerConfigs(jsonDeserializer), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PasswordResetMessage> kafkaPasswordResetListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PasswordResetMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(passwordResetKafkaConsumerFactory());
        return factory;
    }
}
