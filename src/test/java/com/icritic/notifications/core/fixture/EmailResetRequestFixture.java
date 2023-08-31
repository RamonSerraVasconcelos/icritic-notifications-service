package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.EmailResetRequest;

public class EmailResetRequestFixture {

    public static EmailResetRequest load() {
        return EmailResetRequest.builder()
                .userId(1L)
                .email("test@test.test")
                .emailResetHash("emailResetHash")
                .build();
    }
}
