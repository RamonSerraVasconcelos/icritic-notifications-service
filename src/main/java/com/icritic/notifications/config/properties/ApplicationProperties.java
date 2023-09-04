package com.icritic.notifications.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.application.properties")
@Getter
@Setter
public class ApplicationProperties {

    private String kafkaPasswordResetRequestTopic;
    private String kafkaPasswordResetTopic;
    private String kafkaEmailResetRequestTopic;
    private String kafkaGroupId;
}
