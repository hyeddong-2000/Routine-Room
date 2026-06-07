package com.routineroom.mapper.routine;

import com.routineroom.dto.routine.TaskResponseDTO;
import com.routineroom.entity.routine.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TaskMapper {

    TaskEntity selectById(Long taskId);

    List<TaskResponseDTO.Summary> selectListByRoutineId(Long routineId);

    List<TaskResponseDTO.Summary> selectListByUserId(Long userId);

    int selectCountByRoutineId(Long routineId);

    void insert(TaskEntity task);

    void update(TaskEntity task);

    void updateStatus(
            @Param("taskId") Long taskId,
            @Param("statusCd") String statusCd,
            @Param("lastMdfr") String lastMdfr
    );

    void updateOrderSeq(@Param("taskId") Long taskId, @Param("orderSeq") int orderSeq);

    void deleteById(Long taskId);
}
