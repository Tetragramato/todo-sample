package com.tetragramato.todosample.repository;

import com.tetragramato.todosample.entity.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple repositiry interface, to access, save, delete, etc.
 *
 * @author vivienbrissat
 * Date: 09/10/2019
 */
public interface TodoRepository {

    Optional<Todo> getTodoById(UUID id);

    List<Todo> getAllTodos();

    void saveTodo(Todo todo);

}
