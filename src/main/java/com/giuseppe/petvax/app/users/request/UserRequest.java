package com.giuseppe.petvax.app.users.request;

public record UserRequest(
        String nombres,
        String apellidos,
        String email
) {
}
