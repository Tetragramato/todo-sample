package com.tetragramato.todosample.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Todo Entity : used as Bean and JSON structure.
 *
 * @author vivienbrissat
 * Date: 09/10/2019
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private UUID   id;
    private String name;
    private String content;

}
