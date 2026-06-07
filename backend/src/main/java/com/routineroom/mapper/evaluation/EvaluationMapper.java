package com.routineroom.mapper.evaluation;

import com.routineroom.entity.evaluation.DailyEvaluationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EvaluationMapper {

    DailyEvaluationEntity selectByUserNoAndDate(
            @Param("userNo") Integer userNo,
            @Param("evalDate") LocalDate evalDate
    );

    List<DailyEvaluationEntity> selectListByUserNo(
            @Param("userNo") Integer userNo,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    int selectCountByUserNoAndDate(
            @Param("userNo") Integer userNo,
            @Param("evalDate") LocalDate evalDate
    );

    void insert(DailyEvaluationEntity evaluation);

    void update(DailyEvaluationEntity evaluation);
}
