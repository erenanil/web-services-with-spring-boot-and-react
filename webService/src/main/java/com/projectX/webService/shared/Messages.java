package com.projectX.webService.shared;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    public static String getMessagesForLocale(String messageKey, Locale locale){
        return ResourceBundle.getBundle("messages", locale).getString(messageKey);
    }
}