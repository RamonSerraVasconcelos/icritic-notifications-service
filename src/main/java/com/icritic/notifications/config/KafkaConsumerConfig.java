package com.icritic.notifications.config;

import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.config.properties.KafkaProperties;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetMessage;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetRequestMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private ApplicationProperties applicationProperties;

    public Map<String, Object> consumerConfigs(JsonDeserializer jsonDeserializer) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, applicationProperties.getKafkaGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer);
        return props;
    }

    @Bean
    public ConsumerFactory<String, PasswordResetRequestMessage> passwordResetRequestKafkaConsumerFactory() {
        DefaultJackson2JavaTypeMapper jsonMapper = new DefaultJackson2JavaTypeMapper();
        jsonMapper.addTrustedPackages("*");

        JsonDeserializer jsonDeserializer = new JsonDeserializer<>(PasswordResetRequestMessage.class);
        jsonDeserializer.setTypeMapper(jsonMapper);

        return new DefaultKafkaConsumerFactory<>( consumerConfigs(jsonDeserializer), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PasswordResetRequestMessage> kafkaPasswordResetRequestListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PasswordResetRequestMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(passwordResetRequestKafkaConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, PasswordResetMessage> passwordResetKafkaConsumerFactory() {
        DefaultJackson2JavaTypeMapper jsonMapper = new DefaultJackson2JavaTypeMapper();
        jsonMapper.addTrustedPackages("*");

        JsonDeserializer jsonDeserializer = new JsonDeserializer<>(PasswordResetMessage.class);
        jsonDeserializer.setTypeMapper(jsonMapper);

        return new DefaultKafkaConsumerFactory<>( consumerConfigs(jsonDeserializer), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PasswordResetMessage> kafkaPasswordResetListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PasswordResetMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(passwordResetKafkaConsumerFactory());
        return factory;
    }
}
