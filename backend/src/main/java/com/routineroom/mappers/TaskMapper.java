package com.routineroom.mappers;

import com.routineroom.dtos.TaskResponseDTO;
import com.routineroom.entities.TaskEntity;
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
            @Param("lastMdfr") String lastMdfr,
            @Param("lastMdfcnDt") LocalDateTime lastMdfcnDt
    );

    void updateOrderSeq(@Param("taskId") Long taskId, @Param("orderSeq") int orderSeq);

    void deleteById(Long taskId);
}
