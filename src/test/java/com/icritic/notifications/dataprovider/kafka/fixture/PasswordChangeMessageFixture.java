package com.icritic.notifications.dataprovider.kafka.fixture;

import com.icritic.notifications.dataprovider.kafka.entity.PasswordChangeMessage;

public class PasswordChangeMessageFixture {

    public static PasswordChangeMessage load() {
        return PasswordChangeMessage.builder()
                .userId(1L)
                .email("l5fQG@example.com")
                .build();
    }
}
