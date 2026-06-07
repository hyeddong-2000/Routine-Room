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
public class RoutineTodoDtlEntity extends BaseEntity {

    private Integer todoId;
    private Integer routineId;
    private Integer userNo;
    private LocalDate targetDate;
    private String statusCd;
    private Integer certFileId;
}
