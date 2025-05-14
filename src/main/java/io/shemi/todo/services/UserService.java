package io.shemi.todo.services;

import io.shemi.todo.exceptions.UserAlreadyTaken;
import io.shemi.todo.exceptions.UserNotFound;
import io.shemi.todo.jwt.JwtUtil;
import io.shemi.todo.models.User;
import io.shemi.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, JwtUtil jwtUtil,
            AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> register(User user){
        if(repository.findByUsername(user.getUsername()).isPresent()){
            throw new UserAlreadyTaken();
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = repository.save(newUser);
        HashMap<String,Object> res = new HashMap<>();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(savedUser.getUsername(),user.getPassword()));
        String token = jwtUtil.generateToken(savedUser.getUsername());
        res.put("token",token);
        res.put("user",savedUser);
        res.put("message","User registered successfully");
        return res;
    }

    public Map<String,String> login(String username,String password){
        User user = repository.findByUsername(username).orElseThrow(()->new UserNotFound());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        HashMap<String,String> res = new HashMap<>();
        String token = jwtUtil.generateToken(user.getUsername());
        res.put("token",token);
        res.put("message","Logged in successfully");
        return res;
    }

    public void logout(){
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        return repository.findById(id).orElseThrow(()->new UserNotFound());
    }

    public Optional<User> updateUser(Long id, User user){
        if(!repository.existsById(id)){
            throw new UserNotFound();
        }
        return repository.findById(id).map(u->{
            if(user.getUsername()!=null){
                u.setUsername(user.getUsername());
            }
            if(user.getPassword() != null){
                u.setPassword(user.getPassword());
            }
            return repository.save(u);
        });
    }

    public void deleteUser(Long id){
        if(!repository.existsById(id)){
            throw new UserNotFound();
        }
        repository.deleteById(id);
    }
}
