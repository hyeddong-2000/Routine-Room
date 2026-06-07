package com.routineroom.entity.routine;

import com.routineroom.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineEntity extends BaseEntity {

    private Integer routineId;
    private Integer userNo;
    private String title;
    private String content;
    private String categoryCd;
    private String cronExpr;
    private boolean isActive;
}
