package com.routineroom.common.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseWrapper {

    private int code;
    private String message;
    private Object data;

    public static ResponseWrapper success(Object data) {
        return ResponseWrapper.builder()
                .code(200)
                .message("정상 처리되었습니다.")
                .data(data)
                .build();
    }

    public static ResponseWrapper success() {
        return ResponseWrapper.builder()
                .code(200)
                .message("정상 처리되었습니다.")
                .build();
    }

    public static ResponseWrapper error(ErrorCode errorCode) {
        return ResponseWrapper.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    public static ResponseWrapper error(int code, String message) {
        return ResponseWrapper.builder()
                .code(code)
                .message(message)
                .build();
    }
}
