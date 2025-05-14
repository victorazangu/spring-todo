package io.shemi.todo.services;

import io.shemi.todo.dtos.Mapper;
import io.shemi.todo.exceptions.TodoNotFound;
import io.shemi.todo.models.Todo;
import io.shemi.todo.records.todos.CreateTodo;
import io.shemi.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TodoService {
    public final TodoRepository repository;
    public final Mapper mapper = new Mapper();

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    //    create todo
    public Todo createTodo(Todo t) {
        return repository.save(t);
    }

    //    get all todo
    public List<Todo> getAllTodo() {
        if(repository.count()==0){
            throw new TodoNotFound();
        }
        return repository.findAll();
    }

    // get by id
    public Optional<Todo> getTodoById(Long id) {
        if (!repository.existsById(id)) {
            throw new TodoNotFound();
        }
        return Optional.ofNullable(repository.findById(id)).orElseThrow(() -> new TodoNotFound());
    }


    //    update todo
    public Optional<Todo> updateTodo(Long id, Todo t) {
        if (!repository.existsById(id)) {
            throw new TodoNotFound();
        }
        return repository.findById(id).map(todo -> {
                    if (t.getTitle() != null) {
                        todo.setTitle(t.getTitle());
                    }
                    if (t.getDescription() != null) {
                        todo.setDescription(t.getDescription());
                    }
                    if (t.isCompleted()) {
                        todo.setCompleted(t.isCompleted());
                    }
                    return repository.save(todo);
                }
        );
    }


    // delete to do

    public void deleteTodo(Long id) {
        if (!repository.existsById(id)) {
            throw new TodoNotFound();
        }
        repository.deleteById(id);
    }
}
