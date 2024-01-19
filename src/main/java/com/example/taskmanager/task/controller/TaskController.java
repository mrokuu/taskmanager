package com.example.taskmanager.task.controller;


import com.example.taskmanager.task.dto.request.DTSearchRequestDTO;
import com.example.taskmanager.task.dto.response.DTRecordsResponseDTO;
import com.example.taskmanager.task.dto.response.TaskResponseDTO;
import com.example.taskmanager.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/list")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping("/records")
    public ResponseEntity<DTRecordsResponseDTO> searchRecords(@RequestBody DTSearchRequestDTO dtSearchRequestDTO) {
        List<TaskResponseDTO> records = taskService.searchRecords(dtSearchRequestDTO);
        long recordsTotal = taskService.countAll();
        int recordsFiltered = taskService.countAll(dtSearchRequestDTO);
        DTRecordsResponseDTO dtRecordsResponseDTO = new DTRecordsResponseDTO(records, recordsTotal, recordsFiltered);

        return new ResponseEntity<>(dtRecordsResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
