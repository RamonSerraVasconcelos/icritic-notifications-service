package com.icritic.notifications.core.usecase;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.icritic.notifications.core.fixture.ExternalNotificationFixture;
import com.icritic.notifications.core.model.ExternalNotification;
import com.icritic.notifications.core.usecase.boundary.SaveExternalNotificationBoundary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveExternalNotificationUseCaseTest {

    @InjectMocks
    private SaveExternalNotificationUseCase saveNotificationUseCase;

    @Mock
    private SaveExternalNotificationBoundary saveNotificationBoundary;

    @Test
    void givenValidNotification_thenCallSaveNotificationBoundary() {
        ExternalNotification notification = ExternalNotificationFixture.load();

        saveNotificationUseCase.execute(notification);

        verify(saveNotificationBoundary).execute(notification);
    }

    @Test
    void givenError_whenSendingMessage_ThenCaptureException_andLogError() {
        ExternalNotification notification = ExternalNotificationFixture.load();
        String logError = "[ERROR] Error saving external notification with id: [00000000-0000-0000-0000-000000000000]";

        Logger logger = (Logger) LoggerFactory.getLogger(SaveExternalNotificationUseCase.class);
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        loggerContext.getLogger("com.icritic.notifications.core.usecase.SaveExternalNotificationUseCase").addAppender(listAppender);

        doThrow(new RuntimeException("Mongodb error")).when(saveNotificationBoundary).execute(notification);

        saveNotificationUseCase.execute(notification);

        List<ILoggingEvent> loggingEvents = listAppender.list;
        String loggedError = loggingEvents.toArray()[1].toString();
        assertEquals(logError.length(), loggedError.length());
    }
}
