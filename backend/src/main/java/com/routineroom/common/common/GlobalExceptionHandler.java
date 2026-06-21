package com.routineroom.common.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonApiException.class)
    public ResponseEntity<ResponseWrapper> handleCommonApiException(CommonApiException ex) {
        return CommonExceptionHandler.commonHandler(ex);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseWrapper> handleBindException(BindException ex) {
        return CommonExceptionHandler.handleBindException(ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseWrapper> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return CommonExceptionHandler.handleMethodNotSupportedException(ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseWrapper> handleAccessDeniedException(AccessDeniedException ex) {
        return CommonExceptionHandler.handleAccessDeniedException(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper> handleException(Exception ex) {
        return CommonExceptionHandler.handleException(ex);
    }
}
