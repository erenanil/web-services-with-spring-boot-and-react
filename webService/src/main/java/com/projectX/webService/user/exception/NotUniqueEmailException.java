package com.projectX.webService.user.exception;

import java.util.Collections;
import java.util.Map;
import org.springframework.context.i18n.LocaleContextHolder;

import com.projectX.webService.shared.Messages;

public class NotUniqueEmailException extends RuntimeException {
    public NotUniqueEmailException(){
        super(Messages.getMessageForLocale("projectX.error.validation", LocaleContextHolder.getLocale()));
    }
    public Map<String,String> getValidationErrors(){
        return Collections.singletonMap("email" , Messages.getMessageForLocale("projectX.constraint.email.notunique",LocaleContextHolder.getLocale()));
    }
}
