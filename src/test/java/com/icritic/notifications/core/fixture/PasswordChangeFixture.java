package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.PasswordChange;

public class PasswordChangeFixture {

    public static PasswordChange load() {
        return PasswordChange.builder()
                .userId(1L)
                .email("test@test.test")
                .build();
    }
}
