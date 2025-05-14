package io.shemi.todo.records.todos;

import jakarta.validation.constraints.NotNull;

public record CreateTodo(
        @NotNull(message = "Title should not be null")
        String title,
        @NotNull(message = "Description should not be null")
        String description
) {
}
