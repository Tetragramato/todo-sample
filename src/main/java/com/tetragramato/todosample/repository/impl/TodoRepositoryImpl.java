package com.tetragramato.todosample.repository.impl;

import com.tetragramato.todosample.entity.Todo;
import com.tetragramato.todosample.repository.TodoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * TodoRepository implementation : just manipulate a simple Array, for dev/demonstration purpose.
 *
 * @author vivienbrissat
 * Date: 09/10/2019
 */
@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private List<Todo> repoTodos = new ArrayList<>();

    @Override
    public Optional<Todo> getTodoById(final UUID id) {
        return repoTodos.stream()
                        .filter(e -> id.equals(e.getId()))
                        .findFirst();
    }

    @Override
    public List<Todo> getAllTodos() {
        return repoTodos;
    }

    @Override
    public void saveTodo(final Todo todo) {
        if (todo.getId() == null) {
            todo.setId(UUID.randomUUID());
        }
        repoTodos.add(todo);
    }
}
