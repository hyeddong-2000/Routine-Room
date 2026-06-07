package com.routineroom.controllers;

import com.routineroom.common.ResponseWrapper;
import com.routineroom.dtos.TaskRequestDTO;
import com.routineroom.dtos.TaskResponseDTO;
import com.routineroom.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper<TaskResponseDTO.Detail>> getTask(
            @PathVariable Long taskId
    ) {
        return ResponseEntity.ok(ResponseWrapper.success(taskService.getTask(taskId)));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<TaskResponseDTO.Summary>>> getTaskList(
            @RequestParam Long routineId
    ) {
        return ResponseEntity.ok(ResponseWrapper.success(taskService.getTaskListByRoutine(routineId)));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Void>> registerTask(
            @RequestBody @Valid TaskRequestDTO.Create request,
            @AuthenticationPrincipal String userId
    ) {
        taskService.createTask(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success());
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper<Void>> updateTask(
            @PathVariable Long taskId,
            @RequestBody @Valid TaskRequestDTO.Modify request,
            @AuthenticationPrincipal String userId
    ) {
        taskService.modifyTask(taskId, request, userId);
        return ResponseEntity.ok(ResponseWrapper.success());
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<ResponseWrapper<Void>> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestBody @Valid TaskRequestDTO.StatusChange request,
            @AuthenticationPrincipal String userId
    ) {
        taskService.modifyTaskStatus(taskId, request, userId);
        return ResponseEntity.ok(ResponseWrapper.success());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteTask(
            @PathVariable Long taskId
    ) {
        taskService.removeTask(taskId);
        return ResponseEntity.ok(ResponseWrapper.success());
    }
}
