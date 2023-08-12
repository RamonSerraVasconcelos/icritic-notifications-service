package com.icritic.notifications.dataprovider.kafka.fixture;

import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetMessage;

public class PasswordResetMessageFixture {

    public static PasswordResetMessage load() {
        return PasswordResetMessage.builder()
                .userId(1L)
                .email("test@test.test")
                .build();
    }
}
