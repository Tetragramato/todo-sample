package com.tetragramato.todosample.controller

import com.tetragramato.todosample.entity.Todo
import com.tetragramato.todosample.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

/**
 * Controller for Todos.
 *
 * @author Brissat
 */

@CrossOrigin //Only for dev purposes
@RestController
class TodoController(private val todoRepository: TodoRepository) {

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    fun postTodo(@RequestBody todo: Todo) {
        todoRepository.saveTodo(todo)
    }

    @GetMapping("/todos")
    fun getAllTodos(): ResponseEntity<List<Todo>> {
        return ResponseEntity(todoRepository.getAllTodos(), HttpStatus.OK)
    }

    @GetMapping("/todos/{id}")
    fun getTodoById(@PathVariable id: UUID): ResponseEntity<Todo> {
        val todo: Todo = todoRepository.getTodoById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Todo Not Found") }
        return ResponseEntity(todo, HttpStatus.OK)
    }

}
