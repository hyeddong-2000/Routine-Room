package com.routineroom.entity.evaluation;

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
public class DailyEvaluationEntity extends BaseEntity {

    private Integer evalId;
    private Integer userNo;
    private LocalDate evalDate;
    private int scoreWork;
    private int scoreStudy;
    private int scoreExercise;
    private int scoreDiet;
    private int waterIntakeMl;
    private boolean takenSupplements;
    private String dailyComment;
}
