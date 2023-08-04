package com.icritic.notifications.core.usecase;

import com.icritic.notifications.core.fixture.EmailFixture;
import com.icritic.notifications.core.fixture.ExternalNotificationFixture;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.usecase.boundary.SendEmailBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendEmailNotificationUseCaseTest {

    @InjectMocks
    private SendEmailNotificationUseCase sendEmailUseCase;

    @Mock
    private SaveExternalNotificationUseCase saveNotificationUseCase;

    @Mock
    private SendEmailBoundary sendEmailBoundary;

    @Captor
    private ArgumentCaptor<ExternalNotification> externalNotificationArgumentCaptor;

    @Test
    void givenValidEmailAndNotification_thenCallBoundary_andSaveNotificationWithSentTrue() throws Exception{
        Email email = EmailFixture.load();
        ExternalNotification notification = ExternalNotificationFixture.load();

        doNothing().when(saveNotificationUseCase).execute(externalNotificationArgumentCaptor.capture());

        sendEmailUseCase.execute(email, notification);

        verify(saveNotificationUseCase).execute(notification);
        assertTrue(externalNotificationArgumentCaptor.getValue().isSent());
    }

    @Test
    void givenNullPointerException_thenLogError_AndSaveNotificationWithSentFalse() throws Exception{
        Email email = EmailFixture.load();
        ExternalNotification notification = ExternalNotificationFixture.load();

        doThrow(RuntimeException.class).when(sendEmailBoundary).execute(email);

        assertThrows(RuntimeException.class, () -> sendEmailUseCase.execute(email, notification));

        verify(saveNotificationUseCase).execute(externalNotificationArgumentCaptor.capture());
        assertFalse(externalNotificationArgumentCaptor.getValue().isSent());
    }
}
