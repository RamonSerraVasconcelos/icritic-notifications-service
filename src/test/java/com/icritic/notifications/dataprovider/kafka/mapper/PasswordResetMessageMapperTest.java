package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.PasswordReset;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetMessage;
import com.icritic.notifications.dataprovider.kafka.fixture.PasswordResetMessageFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordResetMessageMapperTest {

    @Test
    void passwordResetMessage_thenReturnPasswordReset() {
        PasswordResetMessage passwordResetMessage = PasswordResetMessageFixture.load();

        PasswordResetMessageMapper mapper = PasswordResetMessageMapper.INSTANCE;

        PasswordReset passwordReset = mapper.passwordResetMessageToPasswordReset(passwordResetMessage);

        assertEquals(passwordResetMessage.getUserId(), passwordReset.getUserId());
        assertEquals(passwordResetMessage.getEmail(), passwordReset.getEmail());
    }
}
