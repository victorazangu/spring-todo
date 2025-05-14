package io.shemi.todo.records.users;

import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull(message = "Usename should not be null")
        String username,
        @NotNull(message = "Password should not be null")
        String password
) {
}
