package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.PasswordReset;

public class PasswordResetFixture {

    public static PasswordReset load() {
        return PasswordReset.builder()
                .userId(1L)
                .email("test@test.test")
                .build();
    }
}
