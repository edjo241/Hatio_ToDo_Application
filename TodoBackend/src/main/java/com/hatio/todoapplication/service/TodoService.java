package com.hatio.todoapplication.service;

import com.hatio.todoapplication.dto.TodoDto;
import com.hatio.todoapplication.exceptions.project.ProjectNotFoundException;
import com.hatio.todoapplication.exceptions.todo.TodoNotFoundException;
import com.hatio.todoapplication.model.Project;
import com.hatio.todoapplication.model.Todo;
import com.hatio.todoapplication.repository.ProjectRepository;
import com.hatio.todoapplication.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;

    public TodoService(TodoRepository todoRepository, ProjectRepository projectRepository) {
        this.todoRepository = todoRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<Todo> getTodoByIdService(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo with the corresponding Id not "));
        return ResponseEntity.ok(todo);
    }

    public ResponseEntity<Todo> createTodoService(TodoDto todo) {
        Todo newTodo = new Todo();
        newTodo.setCreatedDate(new Date());
        newTodo.setUpdatedDate(new Date());
        newTodo.setDescription(todo.getDescription());
        newTodo.setTitle(todo.getTitle());
        newTodo.setDone(false);

        Project project = projectRepository.findById(todo.getProjectId()).orElseThrow(() -> new ProjectNotFoundException("Project with the corresponding ID not found"));
        newTodo.setProject(project);

        return ResponseEntity.ok(todoRepository.save(newTodo));

    }


    public ResponseEntity<Todo> updateTodoService(Integer id, TodoDto todo) {

        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo with the corresponding id not found"));

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDescription(todo.getDescription());

        return ResponseEntity.ok(todoRepository.save(existingTodo));
    }

    public ResponseEntity<String> deleteTodoService(Integer id) {

        Todo existingTodo = todoRepository.findById(id).orElseThrow(()->new TodoNotFoundException("Todo with the corresponding id not found"));
        if(existingTodo!=null) {
            Project project = existingTodo.getProject();
            if (project != null) {
                project.getTodoList().remove(existingTodo);
            }
            todoRepository.delete(existingTodo);
            return ResponseEntity.ok("Todo with ID: " + id + " has been deleted");
        }else {
            throw new TodoNotFoundException("Todo with corresponding ID not found");
        }

    }

    public ResponseEntity<Todo> doneTodoService(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo with corresponding ID not found"));
        if(todo.isDone()){
            todo.setDone(false);
        }else{
            todo.setDone(true);
        }
        return ResponseEntity.ok(todoRepository.save(todo));
    }
}

