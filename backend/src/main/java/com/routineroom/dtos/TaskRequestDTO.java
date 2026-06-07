package com.routineroom.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class TaskRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        @NotBlank
        private String title;

        private String content;

        @NotBlank
        private String priorityCd;

        private LocalDate dueDt;

        private Long assigneeId;

        @NotNull
        private Long routineId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Modify {
        @NotBlank
        private String title;

        private String content;

        @NotBlank
        private String priorityCd;

        private LocalDate dueDt;

        private Long assigneeId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusChange {
        @NotBlank
        private String statusCd;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderUpdate {
        @NotNull
        private Long taskId;

        @NotNull
        private Integer orderSeq;
    }
}
