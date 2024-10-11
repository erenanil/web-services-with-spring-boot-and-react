package com.projectX.webService.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.projectX.webService.shared.Messages;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
        super(Messages.getMessageForLocale("projectx.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
