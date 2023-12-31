package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.PasswordResetRequest;
import com.icritic.notifications.dataprovider.kafka.fixture.PasswordResetRequestMessageFixture;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordResetRequestMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordResetRequestMessageMapperTest {

    @Test
    void givenValidPasswordResetRequestMessage_thenReturnPasswordResetRequest() {
        PasswordResetRequestMessage passwordResetRequestMessage = PasswordResetRequestMessageFixture.load();

        PasswordResetRequestMessageMapper mapper = PasswordResetRequestMessageMapper.INSTANCE;

        PasswordResetRequest passwordResetRequest = mapper.passwordResetRequestMessageToPasswordResetRequest(passwordResetRequestMessage);

        assertEquals(passwordResetRequestMessage.getUserId(), passwordResetRequest.getUserId());
        assertEquals(passwordResetRequestMessage.getEmail(), passwordResetRequest.getEmail());
        assertEquals(passwordResetRequestMessage.getPasswordResetHash(), passwordResetRequest.getPasswordResetHash());
    }
}
