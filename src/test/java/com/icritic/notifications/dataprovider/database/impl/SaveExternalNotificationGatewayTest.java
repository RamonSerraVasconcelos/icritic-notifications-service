package com.icritic.notifications.dataprovider.database.impl;

import com.icritic.notifications.core.fixture.ExternalNotificationFixture;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.dataprovider.database.entity.ExternalNotificationEntity;
import com.icritic.notifications.dataprovider.database.repository.ExternalNotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class SaveExternalNotificationGatewayTest {

    @InjectMocks
    private SaveExternalNotificationGateway saveExternalNotificationGateway;

    @Mock
    private ExternalNotificationRepository repository;

    @Test
    void givenValidExternalNotification_thenSaveNotification() {
        ExternalNotification externalNotification = ExternalNotificationFixture.load();

        saveExternalNotificationGateway.execute(externalNotification);

        verify(repository).save(any(ExternalNotificationEntity.class));
        verifyNoMoreInteractions(repository);
    }
}
