package io.shemi.todo.controllers;

import io.shemi.todo.models.User;
import io.shemi.todo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    //register
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        return ResponseEntity.ok(service.register(user));
    }


//    login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        return ResponseEntity.ok(service.login(user.getUsername(), user.getPassword()));
    }


    //    get all
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}
