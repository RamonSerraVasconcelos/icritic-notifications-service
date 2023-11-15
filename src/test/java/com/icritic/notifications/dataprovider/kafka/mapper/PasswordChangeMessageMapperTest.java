package com.icritic.notifications.dataprovider.kafka.mapper;

import com.icritic.notifications.core.model.PasswordChange;
import com.icritic.notifications.dataprovider.kafka.entity.PasswordChangeMessage;
import com.icritic.notifications.dataprovider.kafka.fixture.PasswordChangeMessageFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordChangeMessageMapperTest {

    @Test
    void givenPasswordChangeMessage_ThenReturnPasswordChange() {
        PasswordChangeMessage passwordChangeMessage = PasswordChangeMessageFixture.load();

        PasswordChange passwordChange = PasswordChangeMessageMapper.INSTANCE.passwordChangeMessageToPasswordChange(passwordChangeMessage);

        assertThat(passwordChange).isNotNull().usingRecursiveComparison().isEqualTo(passwordChangeMessage);
    }
}
