package com.icritic.notifications.core.usecase;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.fixture.PasswordResetFixture;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.model.PasswordReset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendPasswordResetNotificationUseCaseTest {

    @InjectMocks
    private SendPasswordResetNotificationUseCase sendPasswordResetNotificationUseCase;

    @Mock
    private SendEmailNotificationUseCase sendEmailNotificationUseCase;

    @Mock
    private ApplicationProperties applicationProperties;

    @Captor
    private ArgumentCaptor<Email> emailArgumentCaptor;

    @Captor
    private ArgumentCaptor<ExternalNotification> notificationArgumentCaptor;

    @Test
    void givenValidPasswordReset_thenBuildEmailAndNotification_andCallUseCases() throws Exception {
        PasswordReset passwordReset = PasswordResetFixture.load();

        when(applicationProperties.getKafkaPasswordResetRequestTopic()).thenReturn("reset-password-topic");

        sendPasswordResetNotificationUseCase.execute(passwordReset);

        verify(sendEmailNotificationUseCase).execute(emailArgumentCaptor.capture(), notificationArgumentCaptor.capture());

        assertEquals(passwordReset.getEmail(), emailArgumentCaptor.getValue().getTo());
        assertEquals(passwordReset.getUserId(), notificationArgumentCaptor.getValue().getNotifierId());
    }

    @Test
    void givenException_thenLogError() throws Exception {
        PasswordReset passwordReset = PasswordResetFixture.load();
        String logError = "[ERROR] Error sending password reset notification to email: [test@test.test]. Error: [Usecase error]";

        Logger logger = (Logger) LoggerFactory.getLogger(SendPasswordResetNotificationUseCase.class);
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        loggerContext.getLogger("com.icritic.notifications.core.usecase.SendPasswordResetNotificationUseCase").addAppender(listAppender);

        doThrow(new RuntimeException("Usecase error")).when(sendEmailNotificationUseCase).execute(any(), any());

        sendPasswordResetNotificationUseCase.execute(passwordReset);

        List<ILoggingEvent> loggingEvents = listAppender.list;
        String loggedError = loggingEvents.toArray()[0].toString();

        verify(sendEmailNotificationUseCase).execute(any(), any());
        assertEquals(logError, loggedError);
    }
}
