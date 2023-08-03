package com.icritic.notifications.dataprovider.mail.impl;

import com.icritic.notifications.core.fixture.EmailFixture;
import com.icritic.notifications.core.model.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendEmailGatewayTest {

    @InjectMocks
    private SendEmailGateway sendEmailGateway;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void givenValidEmail_thenSendEmail() throws Exception {
        Email email = EmailFixture.load();

        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        sendEmailGateway.execute(email);

        verify(javaMailSender).send(any(MimeMessage.class));
    }
}
