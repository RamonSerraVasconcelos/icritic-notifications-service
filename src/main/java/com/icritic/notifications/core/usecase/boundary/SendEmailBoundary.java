package com.icritic.notifications.core.usecase.boundary;

import com.icritic.notifications.core.model.Email;

public interface SendEmailBoundary {

    void execute(Email email);
}
