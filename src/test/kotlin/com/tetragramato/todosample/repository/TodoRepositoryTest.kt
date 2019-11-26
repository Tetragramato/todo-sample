package com.tetragramato.todosample.repository

import com.tetragramato.todosample.entity.Todo
import org.assertj.core.api.AssertionsForClassTypes.assertThat

import org.junit.jupiter.api.Test
import java.util.*

/**
 * @author Brissat
 */
class TodoRepositoryTest {

    @Test
    fun getTodoById() {
        //Given
        val todoRepository: TodoRepository = TodoRepositoryImpl()
        val uuid = UUID.randomUUID()
        val todo = Todo(uuid, "toto", "titi")
        todoRepository.saveTodo(todo)
        //When
        val todoFromRepo: Optional<Todo> = todoRepository.getTodoById(uuid)
        //Then
        assertThat(todoFromRepo).isNotEmpty
        assertThat(todoFromRepo.get()).isEqualTo(todo)
    }

    @Test
    fun getAllTodos() {
        //Given
        val todoRepository: TodoRepository = TodoRepositoryImpl()
        val uuid = UUID.randomUUID()
        val todo = Todo(uuid, "toto", "titi")
        todoRepository.saveTodo(todo)
        //When
        val todosFromRepo: List<Todo> = todoRepository.getAllTodos()
        //Then
        assertThat(todosFromRepo).isNotNull
        assertThat(todosFromRepo.size).isEqualTo(1)
    }
}