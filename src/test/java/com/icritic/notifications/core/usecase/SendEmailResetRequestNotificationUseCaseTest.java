package com.icritic.notifications.core.usecase;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.icritic.notifications.config.properties.ApplicationProperties;
import com.icritic.notifications.core.fixture.EmailResetRequestFixture;
import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.model.EmailResetRequest;
import com.icritic.notifications.core.model.ExternalNotification;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendEmailResetRequestNotificationUseCaseTest {

    @InjectMocks
    private SendEmailResetRequestNotificationUseCase sendEmailResetRequestNotificationUseCase;

    @Mock
    private SendEmailNotificationUseCase sendEmailNotificationUseCase;

    @Mock
    private ApplicationProperties applicationProperties;

    @Captor
    private ArgumentCaptor<Email> emailArgumentCaptor;

    @Captor
    private ArgumentCaptor<ExternalNotification> notificationArgumentCaptor;

    @Test
    void givenValidEmailResetRequest_thenCallBuildEmailAndNotification_andCallSendEmailNotificationUseCase() throws Exception {
        EmailResetRequest emailResetRequest = EmailResetRequestFixture.load();

        sendEmailResetRequestNotificationUseCase.execute(emailResetRequest);

        verify(sendEmailNotificationUseCase).execute(emailArgumentCaptor.capture(), notificationArgumentCaptor.capture());
        assertEquals(emailResetRequest.getEmail(), emailArgumentCaptor.getValue().getTo());
        assertEquals(emailResetRequest.getUserId(), notificationArgumentCaptor.getValue().getNotifierId());
    }

    @Test
    void givenException_thenLogError() throws Exception {
        EmailResetRequest emailResetRequest = EmailResetRequestFixture.load();
        String logError = "[ERROR] Error sending email reset request notification to email: [test@test.test]. Error: [Usecase error]";

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        loggerContext.getLogger("com.icritic.notifications.core.usecase.SendEmailResetRequestNotificationUseCase").addAppender(listAppender);

        doThrow(new RuntimeException("Usecase error")).when(sendEmailNotificationUseCase).execute(emailArgumentCaptor.capture(), notificationArgumentCaptor.capture());

        sendEmailResetRequestNotificationUseCase.execute(emailResetRequest);

        List<ILoggingEvent> loggingEvents = listAppender.list;
        String loggedError = loggingEvents.toArray()[0].toString();

        verify(sendEmailNotificationUseCase).execute(emailArgumentCaptor.capture(), notificationArgumentCaptor.capture());
        assertEquals(logError, loggedError);
    }
}