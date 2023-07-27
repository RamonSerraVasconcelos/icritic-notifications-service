package com.icritic.notifications.dataprovider.mail.impl;

import com.icritic.notifications.core.model.Email;
import com.icritic.notifications.core.usecase.boundary.SendEmailBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailGateway implements SendEmailBoundary {

    @Autowired
    private JavaMailSender javaMailSender;

    public void execute(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        javaMailSender.send(message);
    }
}
