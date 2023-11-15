package com.icritic.notifications.dataprovider.kafka.fixture;

import com.icritic.notifications.dataprovider.kafka.entity.EmailResetMessage;

public class EmailResetMessageFixture {

    public static EmailResetMessage load() {
        return EmailResetMessage.builder()
                .userId(1L)
                .email("j6sZn@example.com")
                .build();
    }
}
