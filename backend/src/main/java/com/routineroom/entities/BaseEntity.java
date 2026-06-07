package com.routineroom.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    private String rgtr;
    private LocalDateTime regDt;
    private String lastMdfr;
    private LocalDateTime lastMdfcnDt;

    public void setFirst(String userId) {
        this.rgtr = userId;
        this.regDt = LocalDateTime.now();
    }

    public void setLast(String userId) {
        this.lastMdfr = userId;
        this.lastMdfcnDt = LocalDateTime.now();
    }
}
