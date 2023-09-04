package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.EmailResetRequest;
import com.icritic.notifications.dataprovider.kafka.entity.EmailResetRequestMessage;
import com.icritic.notifications.dataprovider.kafka.fixture.EmailResetRequestMessageFixture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmailResetRequestMessageMapperTest {

    @Test
    void givenEmailResetRequestMessage_thenReturnEmailResetRequest() {
        EmailResetRequestMessage emailResetRequestMessage = EmailResetRequestMessageFixture.load();

        EmailResetRequestMessageMapper mapper = EmailResetRequestMessageMapper.INSTANCE;

        EmailResetRequest emailResetRequest = mapper.emailResetRequestMessageToemailResetRequest(emailResetRequestMessage);

        assertNotNull(emailResetRequest);
        assertEquals(emailResetRequestMessage.getUserId(), emailResetRequest.getUserId());
        assertEquals(emailResetRequestMessage.getEmail(), emailResetRequest.getEmail());
    }
}
