package io.shemi.todo.exceptions;

public class UserAlreadyTaken extends RuntimeException{
    public UserAlreadyTaken(String message) {
        super(message);
    }
    public UserAlreadyTaken(){
        super("User already taken");
    }
}
