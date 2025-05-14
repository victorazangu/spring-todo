package io.shemi.todo.dtos;


import io.shemi.todo.models.Todo;
import io.shemi.todo.models.User;
import io.shemi.todo.records.todos.CreateTodo;
import io.shemi.todo.records.users.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public User mapLoginRequestToUser(LoginRequest loginRequest) {
        User user = new User();
        user.setUsername(loginRequest.username());
        user.setPassword(loginRequest.password());
        return user;
    }

    public Todo mapCreateTodoToTodo(CreateTodo createTodo){
        Todo todo = new Todo();
        todo.setTitle(createTodo.title());
        todo.setDescription(createTodo.description());
        todo.setCompleted(false);
        return todo;
    }
}
