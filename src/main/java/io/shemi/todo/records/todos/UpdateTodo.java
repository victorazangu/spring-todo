package io.shemi.todo.records.todos;

import jakarta.validation.constraints.NotNull;
public record UpdateTodo (
        String title,
        String description,
        boolean completed
){
}
