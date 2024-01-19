package com.example.taskmanager.task.service;

import com.example.taskmanager.application.component.CurrentUser;
import com.example.taskmanager.application.domain.Task;
import com.example.taskmanager.application.domain.User;
import com.example.taskmanager.login.repository.UserRepository;
import com.example.taskmanager.task.dto.request.DTSearchRequestDTO;
import com.example.taskmanager.task.dto.response.TaskResponseDTO;
import com.example.taskmanager.task.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class TaskService {


    private final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private TaskRepository taskRepository;

    private UserRepository userRepository;

    private CurrentUser currentUser;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, CurrentUser currentUser) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public List<TaskResponseDTO> searchRecords(DTSearchRequestDTO searchRequestDTO) {
        String direction = searchRequestDTO.getOrder().getDir().toUpperCase();
        Sort sort = defineSort(searchRequestDTO, direction);
        Pageable pageable = PageRequest.of(searchRequestDTO.getPage(), searchRequestDTO.getLength(), sort);
        String searchValue = "%" + searchRequestDTO.getSearch().getValue().trim() + "%";

        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());
        List<Task> items = taskRepository.findAll(searchValue, user, pageable).getContent();
        List<TaskResponseDTO> itemResponseDTOs = items.stream().map(this::itemResponseDTO).collect(Collectors.toList());

        LOG.info("searchDTRecords - number of items: " + itemResponseDTOs.size());

        return itemResponseDTOs;
    }

    private TaskResponseDTO itemResponseDTO(Task item) {
        return new TaskResponseDTO(item.getFakeId(), item.getName(), item.getDescription(), item.getDateTimeFrom(),
                item.getDateTimeTo());
    }

    private Sort defineSort(DTSearchRequestDTO searchRequestDTO, String direction) {
        Sort sort = "DESC".equals(direction) ?
                Sort.by(searchRequestDTO.getOrder().getColumn()).descending() :
                Sort.by(searchRequestDTO.getOrder().getColumn()).ascending();
        return sort;
    }

    public int countAll(DTSearchRequestDTO dtReq) {
        String searchValue = "%" + dtReq.getSearch().getValue().trim() + "%";
        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());
        int recordsNumber = taskRepository.countAll(searchValue, user);
        return recordsNumber;
    }

    public void deleteItem(Long id) {
        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());
        Task item = taskRepository.findByCreatorAndFakeId(user, id);
        item.getAppaItemsUsers().add(user);
        taskRepository.delete(item);
    }

    public long countAll() {
        User user = userRepository.findById(currentUser.getId()).orElseThrow(userNotFound());
        return taskRepository.countAll("%%", user);
    }

    private Supplier<? extends RuntimeException> userNotFound() {
        return () -> new RuntimeException("User not found");
    }
}
