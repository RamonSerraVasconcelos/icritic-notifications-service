package com.icritic.notifications.dataprovider.kafka.fixture;

import com.icritic.notifications.dataprovider.kafka.entity.EmailResetRequestMessage;

public class EmailResetRequestMessageFixture {

    public static EmailResetRequestMessage load() {
        return EmailResetRequestMessage.builder()
                .userId(1L)
                .email("email")
                .build();
    }
}
