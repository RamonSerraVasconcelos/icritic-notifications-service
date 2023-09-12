package com.icritic.notifications.dataprovider.kafka.consumer;

import com.icritic.notifications.core.usecase.SendEmailResetNotificationUseCase;
import com.icritic.notifications.dataprovider.kafka.entity.EmailResetMessage;
import com.icritic.notifications.dataprovider.kafka.mapper.EmailResetMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailResetConsumer {

    private final SendEmailResetNotificationUseCase sendEmailResetNotificationUseCase;

    @KafkaListener(topics = "${spring.application.properties.kafka-email-reset-topic}", groupId = "${spring.application.properties.kafka-group-id}", containerFactory = "emailResetListenerContainerFactory")
    public void execute(EmailResetMessage emailResetMessage) {
        try {
            log.info("Received email reset message");

            EmailResetMessageMapper mapper = EmailResetMessageMapper.INSTANCE;

            sendEmailResetNotificationUseCase.execute(mapper.emailResetMessageToEmailReset(emailResetMessage));
        } catch (Exception e) {
            log.error("Error receiving email reset message", e);
        }
    }
}
