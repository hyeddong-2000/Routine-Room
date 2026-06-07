package com.routineroom.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Summary {
        private Long taskId;
        private String title;
        private String statusCd;
        private String statusNm;
        private String priorityCd;
        private String priorityNm;
        private LocalDate dueDt;
        private Long assigneeId;
        private String assigneeNm;
        private int orderSeq;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {
        private Long taskId;
        private Long routineId;
        private String title;
        private String content;
        private String statusCd;
        private String statusNm;
        private String priorityCd;
        private String priorityNm;
        private LocalDate dueDt;
        private Long assigneeId;
        private String assigneeNm;
        private int orderSeq;
        private String rgtr;
        private LocalDateTime regDt;
        private String lastMdfr;
        private LocalDateTime lastMdfcnDt;
    }
}
