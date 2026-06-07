package com.routineroom.service.routine;

import com.routineroom.common.common.CommonApiException;
import com.routineroom.common.common.ErrorCode;
import com.routineroom.common.util.SecurityUtil;
import com.routineroom.dto.routine.TaskRequestDTO;
import com.routineroom.dto.routine.TaskResponseDTO;
import com.routineroom.entity.routine.TaskEntity;
import com.routineroom.mapper.routine.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;

    @Transactional(readOnly = true)
    public TaskResponseDTO.Detail getTask(Long taskId) {
        TaskEntity task = taskMapper.selectById(taskId);
        if (task == null) {
            throw new CommonApiException(ErrorCode.TASK_NOT_FOUND);
        }
        return toDetailDTO(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO.Summary> getTaskListByRoutine(Long routineId) {
        return taskMapper.selectListByRoutineId(routineId);
    }

    @Transactional
    public void createTask(TaskRequestDTO.Create request) {
        String userId = SecurityUtil.getCurrentUserId();
        int nextOrderSeq = taskMapper.selectCountByRoutineId(request.getRoutineId()) + 1;
        TaskEntity task = TaskEntity.builder()
                .routineId(request.getRoutineId())
                .title(request.getTitle())
                .content(request.getContent())
                .priorityCd(request.getPriorityCd())
                .dueDt(request.getDueDt())
                .assigneeId(request.getAssigneeId())
                .statusCd("TODO")
                .orderSeq(nextOrderSeq)
                .build();
        task.setFirst(userId);
        taskMapper.insert(task);
    }

    @Transactional
    public void modifyTask(Long taskId, TaskRequestDTO.Modify request) {
        TaskEntity existing = taskMapper.selectById(taskId);
        if (existing == null) {
            throw new CommonApiException(ErrorCode.TASK_NOT_FOUND);
        }
        String userId = SecurityUtil.getCurrentUserId();
        TaskEntity updated = TaskEntity.builder()
                .taskId(taskId)
                .routineId(existing.getRoutineId())
                .title(request.getTitle())
                .content(request.getContent())
                .priorityCd(request.getPriorityCd())
                .dueDt(request.getDueDt())
                .assigneeId(request.getAssigneeId())
                .statusCd(existing.getStatusCd())
                .orderSeq(existing.getOrderSeq())
                .build();
        updated.setLast(userId);
        taskMapper.update(updated);
    }

    @Transactional
    public void modifyTaskStatus(Long taskId, TaskRequestDTO.StatusChange request) {
        if (taskMapper.selectById(taskId) == null) {
            throw new CommonApiException(ErrorCode.TASK_NOT_FOUND);
        }
        taskMapper.updateStatus(taskId, request.getStatusCd(), SecurityUtil.getCurrentUserId());
    }

    @Transactional
    public void removeTask(Long taskId) {
        if (taskMapper.selectById(taskId) == null) {
            throw new CommonApiException(ErrorCode.TASK_NOT_FOUND);
        }
        taskMapper.deleteById(taskId);
    }

    private TaskResponseDTO.Detail toDetailDTO(TaskEntity task) {
        return TaskResponseDTO.Detail.builder()
                .taskId(task.getTaskId())
                .routineId(task.getRoutineId())
                .title(task.getTitle())
                .content(task.getContent())
                .statusCd(task.getStatusCd())
                .priorityCd(task.getPriorityCd())
                .dueDt(task.getDueDt())
                .assigneeId(task.getAssigneeId())
                .orderSeq(task.getOrderSeq())
                .rgtr(task.getRgtr())
                .regDt(task.getRegDt())
                .lastMdfr(task.getLastMdfr())
                .lastMdfcnDt(task.getLastMdfcnDt())
                .build();
    }
}
