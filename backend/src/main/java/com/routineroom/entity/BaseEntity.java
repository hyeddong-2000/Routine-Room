package com.routineroom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    protected String rgtr;
    protected LocalDateTime regDt;
    protected String lastMdfr;
    protected LocalDateTime lastMdfcnDt;

    public void setFirst(String objName) {
        this.rgtr = objName;
        this.lastMdfr = objName;
    }

    public void setLast(String objName) {
        this.lastMdfr = objName;
    }
}
