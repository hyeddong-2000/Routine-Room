package com.routineroom.common.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommonApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
    private final Object data;

    public CommonApiException(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = null;
    }

    public CommonApiException(ErrorCode errorCode, Object data) {
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = data;
    }

    public CommonApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.code = httpStatus.value();
        this.message = message;
        this.data = null;
    }

    public CommonApiException(ErrorCode errorCode, BindingResult bindingResult) {
        this.httpStatus = errorCode.getHttpStatus();
        this.code = errorCode.getCode();
        this.message = generateErrorMessage(bindingResult.getFieldErrors());
        this.data = null;
    }

    private static String generateErrorMessage(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(";"));
    }
}
