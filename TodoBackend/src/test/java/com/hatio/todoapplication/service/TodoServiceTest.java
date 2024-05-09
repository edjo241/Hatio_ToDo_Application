package com.hatio.todoapplication.service;
import com.hatio.todoapplication.dto.TodoDto;
import com.hatio.todoapplication.exceptions.project.ProjectNotFoundException;
import com.hatio.todoapplication.exceptions.todo.TodoNotFoundException;
import com.hatio.todoapplication.model.Project;
import com.hatio.todoapplication.model.Todo;
import com.hatio.todoapplication.repository.ProjectRepository;
import com.hatio.todoapplication.repository.TodoRepository;
import com.hatio.todoapplication.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTodoByIdService() {
        Todo mockTodo = new Todo();
        mockTodo.setId(1);
        mockTodo.setTitle("Test Todo");
        when(todoRepository.findById(1)).thenReturn(Optional.of(mockTodo));

        ResponseEntity<Todo> response = todoService.getTodoByIdService(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Todo", response.getBody().getTitle());
    }

    @Test
    public void testCreateTodoService() {
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle("Test Todo");
        todoDto.setDescription("Test Description");
        todoDto.setProjectId(1);

        Project mockProject = new Project();
        mockProject.setId(1);
        mockProject.setTitle("Test Project");
        when(projectRepository.findById(1)).thenReturn(Optional.of(mockProject));

        when(todoRepository.save(any(Todo.class))).thenAnswer(invocation -> {
            Todo todo = invocation.getArgument(0);
            todo.setId(1);
            return todo;
        });

        ResponseEntity<Todo> response = todoService.createTodoService(todoDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Todo", response.getBody().getTitle());
        assertEquals("Test Description", response.getBody().getDescription());
        assertFalse(response.getBody().isDone());
    }

    @Test
    void updateTodoService_nonExistingId_shouldThrowException() {

        int id = 100;
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle("Updated Title");
        todoDto.setDescription("Updated Description");

        when(todoRepository.findById(id)).thenReturn(java.util.Optional.empty());


        assertThrows(TodoNotFoundException.class, () -> todoService.updateTodoService(id, todoDto));
        verify(todoRepository, never()).save(any(Todo.class));
    }

    void deleteTodoService_existingId_shouldDeleteTodoAndReturnSuccessMessage() {
        // Arrange
        int id = 1;

        Todo existingTodo = new Todo();
        existingTodo.setId(id);
        Project project = new Project();
        project.getTodoList().add(existingTodo);
        existingTodo.setProject(project);

        when(todoRepository.findById(id)).thenReturn(Optional.of(existingTodo));

        // Act
        ResponseEntity<String> responseEntity = todoService.deleteTodoService(id);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Todo with ID: " + id + " has been deleted", responseEntity.getBody());
        verify(todoRepository, times(1)).delete(existingTodo);
    }

    @Test
    void deleteTodoService_nonExistingId_shouldThrowException() {
        // Arrange
        int id = 1;

        when(todoRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodoService(id));
        verify(todoRepository, never()).delete(any());
    }

}
