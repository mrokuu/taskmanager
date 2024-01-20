package com.example.taskmanager.task.service;

import com.example.taskmanager.application.component.CurrentUser;
import com.example.taskmanager.application.domain.Task;
import com.example.taskmanager.application.domain.User;
import com.example.taskmanager.login.repository.UserRepository;
import com.example.taskmanager.task.dto.request.DTSearchRequestDTO;
import com.example.taskmanager.task.dto.response.TaskResponseDTO;
import com.example.taskmanager.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CurrentUser currentUser;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testDeleteItem() {
        // Arrange
        Long id = 1L;
        when(currentUser.getId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(taskRepository.findByCreatorAndFakeId(any(User.class), anyLong())).thenReturn(new Task());

        // Act
        taskService.deleteItem(id);

        // Assert
        verify(taskRepository, times(1)).delete(any(Task.class));
    }

    @Test
    void testCountAllWithoutSearchValue() {
        // Arrange
        when(currentUser.getId()).thenReturn(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(taskRepository.countAll(anyString(), any(User.class))).thenReturn(5);

        // Act
        long result = taskService.countAll();

        // Assert
        assertEquals(5, result);
        // Add more assertions based on the actual implementation and use cases
    }

}