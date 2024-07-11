package com.giuseppe.petvax.app.notifications.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationNotFound extends RuntimeException {
    private final String message;
}
