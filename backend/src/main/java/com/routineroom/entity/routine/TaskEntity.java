package com.routineroom.entity.routine;

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
public class TaskEntity extends BaseEntity {

    private Long taskId;
    private Long routineId;
    private Integer userNo;
    private String title;
    private String content;
    private String statusCd;
    private String priorityCd;
    private LocalDate dueDt;
    private Long assigneeId;
    private int orderSeq;
}
