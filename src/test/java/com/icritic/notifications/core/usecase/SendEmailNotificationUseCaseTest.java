package com.icritic.notifications.core.usecase;

import com.icritic.notifications.core.fixture.EmailNotificationRequestFixture;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.EmailNotificationRequest;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.usecase.boundary.SendEmailBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    void givenValidEmailAndNotification_thenCallBoundary_andSaveNotificationWithSentTrue() throws Exception {
        EmailNotificationRequest emailNotificationRequest = EmailNotificationRequestFixture.load();

        doNothing().when(saveNotificationUseCase).execute(externalNotificationArgumentCaptor.capture());

        sendEmailUseCase.execute(emailNotificationRequest);

        verify(saveNotificationUseCase).execute(any(ExternalNotification.class));
        assertThat(externalNotificationArgumentCaptor.getValue().isSent()).isTrue();
    }

    @Test
    void givenNullPointerException_thenLogError_AndSaveNotificationWithSentFalse() throws Exception {
        EmailNotificationRequest emailNotificationRequest = EmailNotificationRequestFixture.load();

        doThrow(RuntimeException.class).when(sendEmailBoundary).execute(any(Email.class));

        assertThrows(RuntimeException.class, () -> sendEmailUseCase.execute(emailNotificationRequest));

        verify(saveNotificationUseCase).execute(externalNotificationArgumentCaptor.capture());
        assertThat(externalNotificationArgumentCaptor.getValue().isSent()).isFalse();
    }
}
