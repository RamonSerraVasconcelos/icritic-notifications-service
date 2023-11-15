package com.icritic.notifications.dataprovider.kafka.consumer;

import com.icritic.notifications.core.usecase.SendPasswordChangeNotificationUseCase;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordChangeMessage;
import com.icritic.notifications.dataprovider.kafka.mapper.PasswordChangeMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PasswordChangeConsumer {

    private final SendPasswordChangeNotificationUseCase sendPasswordChangeNotificationUseCase;

    @KafkaListener(topics = "${spring.application.properties.kafka-password-change-topic}", groupId = "${spring.application.properties.kafka-group-id}", containerFactory="passwordChangeListenerContainerFactory")
    public void execute(PasswordChangeMessage passwordChangeMessage) {
        log.info("Received password change message");

        PasswordChangeMessageMapper mapper = PasswordChangeMessageMapper.INSTANCE;

        sendPasswordChangeNotificationUseCase.execute(mapper.passwordChangeMessageToPasswordChange(passwordChangeMessage));
    }
}
