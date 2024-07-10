package com.giuseppe.petvax.app.mail.model;

import lombok.Getter;

@Getter
public enum EmailTemplate {

    REGISTRATION_CONFIRMATION("registrationConfirmation.html", "Welcome to PetVax!"),;

    private final String templateName;
    private final String subject;

    EmailTemplate(String templateName, String subject) {
        this.templateName = templateName;
        this.subject = subject;
    }
}
