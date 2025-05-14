package io.shemi.todo.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }
    public UserNotFound(){
        super("User not found");
    }

}
