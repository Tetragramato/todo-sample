package com.tetragramato.todosample.controller

import com.tetragramato.todosample.entity.Todo
import com.tetragramato.todosample.repository.TodoRepository
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

/**
 * @author Brissat
 */

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class ITTodoControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var todoRepository: TodoRepository

    @Test
    fun `postTodo OK`() {
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"toto\", \"content\": \"titi\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated)
    }

    @Test
    fun `getAllTodos OK`() {
        //Given
        Mockito.`when`(todoRepository.getAllTodos()).thenReturn(listOf(
                Todo(null, "toto", "tata"),
                Todo(null, "titi", "tutu")))

        //When
        this.mockMvc.perform(get("/todos"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.*", hasSize<Any>(2)))
    }

    @Test
    fun `getTodoById OK`() {
        //Given
        val uuid: UUID = UUID.randomUUID()
        Mockito.`when`(todoRepository.getTodoById(uuid)).thenReturn(Optional.of(Todo(uuid, "toto", "tata")))

        //When
        this.mockMvc.perform(get("/todos/" + uuid))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.content").exists())
    }

    @Test
    fun `getTodoById with bad ID`() {
        //Given
        val uuid: UUID = UUID.randomUUID()
        Mockito.`when`(todoRepository.getTodoById(uuid)).thenReturn(Optional.empty())

        //When
        this.mockMvc.perform(get("/todos/" + uuid))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound)
    }
}

