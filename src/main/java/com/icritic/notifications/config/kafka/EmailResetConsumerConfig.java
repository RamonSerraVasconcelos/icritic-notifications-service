package com.icritic.notifications.config.kafka;

import com.icritic.notifications.dataprovider.kafka.entity.EmailResetMessage;
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
public class EmailResetConsumerConfig {

    @Autowired
    private ConsumersConfig consumersConfig;

    @Bean
    public ConsumerFactory<String, EmailResetMessage> emailResetKafkaConsumerFactory() {
        DefaultJackson2JavaTypeMapper jsonMapper = new DefaultJackson2JavaTypeMapper();
        jsonMapper.addTrustedPackages("*");

        JsonDeserializer<EmailResetMessage> jsonDeserializer = new JsonDeserializer<>(EmailResetMessage.class);
        jsonDeserializer.setTypeMapper(jsonMapper);

        return new DefaultKafkaConsumerFactory<>(consumersConfig.consumerConfigs(jsonDeserializer), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailResetMessage> emailResetListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EmailResetMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(emailResetKafkaConsumerFactory());
        return factory;
    }
}
