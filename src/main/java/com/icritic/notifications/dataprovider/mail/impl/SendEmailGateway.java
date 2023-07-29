package com.icritic.notifications.dataprovider.mail.impl;

import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.usecase.boundary.SendEmailBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailGateway implements SendEmailBoundary {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void execute(Email email) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(new InternetAddress(email.getFrom()));
        message.setRecipients(MimeMessage.RecipientType.TO, email.getTo());
        message.setSubject(email.getSubject());
        message.setContent(email.getBody(), "text/html; charset=utf-8");
        javaMailSender.send(message);
    }
}
