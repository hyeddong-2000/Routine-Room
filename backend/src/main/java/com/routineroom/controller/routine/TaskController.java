package com.routineroom.controller.routine;

import com.routineroom.common.common.ResponseWrapper;
import com.routineroom.dto.routine.TaskRequestDTO;
import com.routineroom.dto.routine.TaskResponseDTO;
import com.routineroom.service.routine.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseWrapper> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(ResponseWrapper.success(taskService.getTask(taskId)));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getTaskList(@RequestParam Long routineId) {
        return ResponseEntity.ok(ResponseWrapper.success(taskService.getTaskListByRoutine(routineId)));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> registerTask(
            @RequestBody @Valid TaskRequestDTO.Create request
    ) {
        taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success());
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> updateTask(
            @PathVariable Long taskId,
            @RequestBody @Valid TaskRequestDTO.Modify request
    ) {
        taskService.modifyTask(taskId, request);
        return ResponseEntity.ok(ResponseWrapper.success());
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<ResponseWrapper> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestBody @Valid TaskRequestDTO.StatusChange request
    ) {
        taskService.modifyTaskStatus(taskId, request);
        return ResponseEntity.ok(ResponseWrapper.success());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable Long taskId) {
        taskService.removeTask(taskId);
        return ResponseEntity.ok(ResponseWrapper.success());
    }
}
