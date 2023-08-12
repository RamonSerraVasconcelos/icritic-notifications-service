package com.icritic.notifications.dataprovider.kafka.fixture;

import com.icritic.notifications.core.model.PasswordResetRequest;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetRequestMessage;

import java.util.UUID;

public class PasswordResetRequestMessageFixture {

    public static PasswordResetRequestMessage load() {
        return PasswordResetRequestMessage.builder()
                .userId(1L)
                .email("test@test.test")
                .passwordResetHash(UUID.randomUUID().toString())
                .build();
    }
}
