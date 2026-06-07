package com.routineroom.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {

    private String code;
    private String message;
    private T data;

    public static <T> ResponseWrapper<T> success(T data) {
        return ResponseWrapper.<T>builder()
                .code("SUCCESS")
                .message("정상 처리되었습니다.")
                .data(data)
                .build();
    }

    public static <T> ResponseWrapper<T> success() {
        return ResponseWrapper.<T>builder()
                .code("SUCCESS")
                .message("정상 처리되었습니다.")
                .build();
    }

    public static <T> ResponseWrapper<T> error(ErrorCode errorCode) {
        return ResponseWrapper.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    public static <T> ResponseWrapper<T> error(String code, String message) {
        return ResponseWrapper.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
}
