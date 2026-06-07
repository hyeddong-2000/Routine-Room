package com.routineroom.mapper.routine;

import com.routineroom.entity.routine.RoutineEntity;
import com.routineroom.entity.routine.RoutineTodoDtlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface RoutineMapper {

    RoutineEntity selectById(Integer routineId);

    List<RoutineEntity> selectListByUserNo(Integer userNo);

    void insertRoutine(RoutineEntity routine);

    void updateRoutine(RoutineEntity routine);

    void deleteRoutineById(Integer routineId);

    RoutineTodoDtlEntity selectTodoById(Integer todoId);

    List<RoutineTodoDtlEntity> selectTodoListByDate(
            @Param("userNo") Integer userNo,
            @Param("targetDate") LocalDate targetDate
    );

    void insertTodo(RoutineTodoDtlEntity todo);

    void updateTodoStatus(
            @Param("todoId") Integer todoId,
            @Param("statusCd") String statusCd,
            @Param("lastMdfr") String lastMdfr
    );
}
