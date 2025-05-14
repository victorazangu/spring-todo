package io.shemi.todo.controllers;

import io.shemi.todo.models.Todo;
import io.shemi.todo.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    public  final Logger logger = Logger.getLogger(TodoController.class.getName());
    private final TodoService service;
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public ResponseEntity<Map<String,String>> home(){
        return ResponseEntity.ok(Map.of("data","Welcome to todo app"));
    }


    //create -POST
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo t){
        return ResponseEntity.ok(service.createTodo(t));
    }
    // get all - GET
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo(){
        return ResponseEntity.ok(service.getAllTodo());
    }

    // get one - GET
    @GetMapping("/{id}")
    public  ResponseEntity<Todo> getTodoById(@PathVariable("id") Integer id){
        logger.info("id => "+ id);
        Long idLong = Long.valueOf(id);
        logger.info("idLong => "+ idLong);
        return service.getTodoById(idLong).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // update one - PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") Integer id,@RequestBody Todo t){
        Long idLong = Long.valueOf(id);
        return service.updateTodo(idLong,t).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    // delete one - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Integer id){
        Long idLong = Long.valueOf(id);
        service.deleteTodo(idLong);
        return ResponseEntity.noContent().build();
    }

}
