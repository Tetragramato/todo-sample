package com.tetragramato.todosample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main application class.
 *
 * @author Brissat
 */
@SpringBootApplication
class TodoSampleApplication

/**
 * Main method.
 */
fun main(args: Array<String>) {
    runApplication<TodoSampleApplication>(*args)
}
