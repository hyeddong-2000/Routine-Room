package com.routineroom.mapper.ai;

import com.routineroom.entity.ai.AiWeeklyReportEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AiReportMapper {

    AiWeeklyReportEntity selectByUserNoAndStartDate(
            @Param("userNo") Integer userNo,
            @Param("startDate") LocalDate startDate
    );

    List<AiWeeklyReportEntity> selectListByUserNo(Integer userNo);

    int selectCountByUserNoAndStartDate(
            @Param("userNo") Integer userNo,
            @Param("startDate") LocalDate startDate
    );

    void insert(AiWeeklyReportEntity report);
}
