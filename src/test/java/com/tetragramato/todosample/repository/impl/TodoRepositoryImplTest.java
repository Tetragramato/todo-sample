package com.tetragramato.todosample.repository.impl;

import com.tetragramato.todosample.entity.Todo;
import com.tetragramato.todosample.repository.TodoRepository;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vivienbrissat
 * Date: 09/10/2019
 */
public class TodoRepositoryImplTest {

    @Test
    public void getTodoById() {
        //Given
        TodoRepository todoRepository = new TodoRepositoryImpl();
        UUID uuid = UUID.randomUUID();
        Todo todo = new Todo(uuid, "toto", "titi");
        todoRepository.saveTodo(todo);

        //When
        Optional<Todo> todoFromRepo = todoRepository.getTodoById(uuid);

        //Then
        assertThat(todoFromRepo).isNotEmpty();
        assertThat(todoFromRepo.get()).isEqualTo(todo);
    }

    @Test
    public void getAllTodos() {
        //Given
        TodoRepository todoRepository = new TodoRepositoryImpl();
        UUID uuid = UUID.randomUUID();
        Todo todo = new Todo(uuid, "toto", "titi");
        todoRepository.saveTodo(todo);

        //When
        List<Todo> todosFromRepo = todoRepository.getAllTodos();

        //Then
        assertThat(todosFromRepo).isNotEmpty();
        assertThat(todosFromRepo).hasSize(1);
    }
}