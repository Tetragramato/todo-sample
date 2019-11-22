package com.tetragramato.todosample.controller;

import com.tetragramato.todosample.entity.Todo;
import com.tetragramato.todosample.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Controller for Todos.
 *
 * @author vivienbrissat
 * Date: 09/10/2019
 */
@Slf4j
@CrossOrigin //Only for dev purposes
@RestController()
public class TodoController {

    private TodoRepository todoRepository;

    public TodoController(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public void postTodo(@RequestBody Todo todo) {
        log.info("POST : " + todo.toString());
        todoRepository.saveTodo(todo);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        log.info("GET all values");
        return new ResponseEntity<>(todoRepository.getAllTodos(), HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable UUID id) {
        log.info("GET id : " + id);
        Todo todo = todoRepository.getTodoById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo Not Found"));
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

}
