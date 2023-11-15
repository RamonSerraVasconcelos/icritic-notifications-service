package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.EmailReset;
import com.icritic.notifications.dataprovider.kafka.entity.EmailResetMessage;
import com.icritic.notifications.dataprovider.kafka.fixture.EmailResetMessageFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailResetMessageMapperTest {

    @Test
    void givenEmailResetMessage_ThenReturnEmailReset() {
        EmailResetMessage emailResetMessage = EmailResetMessageFixture.load();

        EmailReset emailReset = EmailResetMessageMapper.INSTANCE.emailResetMessageToEmailReset(emailResetMessage);

        assertThat(emailReset).isNotNull().usingRecursiveComparison().isEqualTo(emailResetMessage);
    }
}
