package com.routineroom.entity.ai;

import com.routineroom.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AiWeeklyReportEntity extends BaseEntity {

    private Integer reportId;
    private Integer userNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private String aiFeedbackContent;
    private String totalGradeCd;
}
