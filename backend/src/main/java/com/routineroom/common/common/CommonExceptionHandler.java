package com.routineroom.common.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.stream.Collectors;

@Slf4j
public class CommonExceptionHandler {

    public static ResponseEntity<ResponseWrapper> commonHandler(CommonApiException ex) {
        log.warn("CommonApiException: code={}, message={}", ex.getCode(), ex.getMessage());
        ResponseWrapper response = ResponseWrapper.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .data(ex.getData())
                .build();
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    public static ResponseEntity<ResponseWrapper> handleBindException(BindException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST_BODY;
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(";"));
        ResponseWrapper response = ResponseWrapper.builder()
                .code(errorCode.getCode())
                .message(message)
                .build();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }

    public static ResponseEntity<ResponseWrapper> handleMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        ErrorCode errorCode = ErrorCode.METHOD_NOT_SUPPORTED;
        ResponseWrapper response = ResponseWrapper.error(errorCode);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(response);
    }

    public static ResponseEntity<ResponseWrapper> handleAccessDeniedException(AccessDeniedException ex) {
        ResponseWrapper response = ResponseWrapper.error(ErrorCode.ACCESS_DENIED);
        return ResponseEntity.status(ErrorCode.ACCESS_DENIED.getHttpStatus()).body(response);
    }

    public static ResponseEntity<ResponseWrapper> handleException(Exception ex) {
        log.error("Unhandled Exception", ex);
        ResponseWrapper response = ResponseWrapper.error(ErrorCode.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus()).body(response);
    }
}
