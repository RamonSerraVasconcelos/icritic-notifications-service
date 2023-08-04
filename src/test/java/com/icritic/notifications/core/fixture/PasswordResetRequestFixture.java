package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.PasswordResetRequest;

import java.util.UUID;

public class PasswordResetRequestFixture {

    public static PasswordResetRequest load() {
        return PasswordResetRequest.builder()
                .userId(1L)
                .email("test@test.test")
                .passwordResetHash(UUID.randomUUID().toString())
                .build();
    }
}
