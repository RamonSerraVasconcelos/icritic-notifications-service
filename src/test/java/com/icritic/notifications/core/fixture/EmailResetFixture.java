package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.EmailReset;

public class EmailResetFixture {

    public static EmailReset load() {
        return EmailReset.builder()
                .userId(1L)
                .email("test@test.test")
                .build();
    }
}
