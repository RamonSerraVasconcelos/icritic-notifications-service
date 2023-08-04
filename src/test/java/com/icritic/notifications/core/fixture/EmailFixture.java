package com.icritic.notifications.core.fixture;

import com.icritic.notifications.core.model.Email;

public class EmailFixture {

    public static Email load() {
        return Email.builder()
                .from("test@test.test")
                .to("test@test.test")
                .subject("test")
                .body("<h1>test</h1>")
                .build();
    }
}
