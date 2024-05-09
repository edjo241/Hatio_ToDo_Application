package com.hatio.todoapplication.controller;

import com.hatio.todoapplication.dto.TodoDto;
import com.hatio.todoapplication.model.Todo;
import com.hatio.todoapplication.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id){
        return todoService.getTodoByIdService(id);
    }

    @PostMapping("/todo/create")
    public ResponseEntity<Todo> createTodo(@RequestBody TodoDto todo){
        return todoService.createTodoService(todo);

    }

    @PostMapping("/todo/update/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody TodoDto todo){
        return todoService.updateTodoService(id,todo);
    }

    @PutMapping("/todo/status/{id}")
    public ResponseEntity<Todo> doneTodo(@PathVariable Integer id){
        return todoService.doneTodoService(id);
    }

    @DeleteMapping("/todo/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Integer id){
        return todoService.deleteTodoService(id);
    }

}
