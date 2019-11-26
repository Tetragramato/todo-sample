package com.tetragramato.todosample.repository

import com.tetragramato.todosample.entity.Todo
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Simple repositiry interface, to access, save, delete, etc.
 *
 * @author Brissat
 */
interface TodoRepository {
    fun getTodoById(id: UUID): Optional<Todo>

    fun getAllTodos(): List<Todo>

    fun saveTodo(todo: Todo)
}

/**
 * [TodoRepository] implementation.
 *
 * Just manipulate a simple Array, for dev/demonstration purpose.
 */
@Repository
class TodoRepositoryImpl : TodoRepository {

    private val repoTodos: MutableList<Todo> = ArrayList()

    override fun getTodoById(id: UUID): Optional<Todo> {
        return repoTodos.stream()
                .filter { e: Todo -> id == e.id }
                .findFirst()
    }

    override fun getAllTodos(): List<Todo> {
        return repoTodos
    }

    override fun saveTodo(todo: Todo) {
        todo.id ?: UUID.randomUUID()
        repoTodos.add(todo)
    }
}
