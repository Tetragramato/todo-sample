package com.tetragramato.todosample.controller;

import com.tetragramato.todosample.entity.Todo;
import com.tetragramato.todosample.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author vivienbrissat
 * Date: 09/10/2019
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class ITTodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void postTodo() throws Exception {

        this.mockMvc.perform(post("/todos")
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .content("{\"name\": \"toto\", \"content\": \"titi\"}"))
                    .andDo(print())
                    .andExpect(status().isCreated());
    }

    @Test
    public void getAllTodos() throws Exception {
        //Given
        when(todoRepository.getAllTodos()).thenReturn(Arrays.asList(new Todo(null,"toto", "tata"), new Todo(null,"titi", "tutu")));

        //When
        this.mockMvc.perform(get("/todos"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.*", hasSize(2)));
    }

    @Test
    public void getTodoById() throws Exception {
        //Given
        UUID uuid = UUID.randomUUID();
        when(todoRepository.getTodoById(uuid)).thenReturn(Optional.of(new Todo(uuid, "toto", "tata")));

        //When
        this.mockMvc.perform(get("/todos/"+uuid))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").exists())
                    .andExpect(jsonPath("$.content").exists());
    }

    @Test
    public void getTodoByBadId() throws Exception {
        //Given
        UUID uuid = UUID.randomUUID();
        when(todoRepository.getTodoById(uuid)).thenReturn(Optional.empty());

        //When
        this.mockMvc.perform(get("/todos/"+uuid))
                    .andDo(print())
                    .andExpect(status().isNotFound());
    }
}