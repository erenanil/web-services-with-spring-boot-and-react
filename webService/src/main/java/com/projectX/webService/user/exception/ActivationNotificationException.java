package com.projectX.webService.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.projectX.webService.shared.Messages;

public class ActivationNotificationException extends RuntimeException {
    public ActivationNotificationException() {
        super(Messages.getMessageForLocale("projectX.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
