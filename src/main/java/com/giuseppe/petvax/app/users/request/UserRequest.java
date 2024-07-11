package com.giuseppe.petvax.app.users.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank
        String nombres,
        @NotBlank
        String apellidos,
        @Email
        @NotBlank
        String email
) {
}
