package com.routineroom.common.common;

import lombok.Getter;

@Getter
public class CommonApiException extends RuntimeException {

    private final ErrorCode errorCode;

    public CommonApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
