package io.shemi.todo.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class TodoNotFound extends RuntimeException {
    public TodoNotFound(String message) {
        super(message);
    }
    public TodoNotFound(){
        super("Todo not found");
    }
}
