package com.tetragramato.todosample.entity

import java.util.*

/**
 * Todo data structure.
 *
 * @author Brissat
 */
data class Todo(
        var id: UUID?,
        val name: String,
        val content: String
)