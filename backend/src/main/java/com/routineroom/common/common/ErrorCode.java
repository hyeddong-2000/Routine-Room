package com.routineroom.common.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, 10001, "유효하지 않은 요청입니다."),
    METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED, 10002, "지원하지 않는 HTTP 메서드입니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 20001, "인증이 필요합니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, 20002, "접근 권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 20003, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 20004, "만료된 토큰입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 30001, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, 30002, "이미 존재하는 사용자입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, 30003, "비밀번호가 일치하지 않습니다."),

    ROUTINE_NOT_FOUND(HttpStatus.NOT_FOUND, 40001, "루틴을 찾을 수 없습니다."),
    TASK_NOT_FOUND(HttpStatus.NOT_FOUND, 40002, "태스크를 찾을 수 없습니다."),
    TASK_ACCESS_DENIED(HttpStatus.FORBIDDEN, 40003, "해당 태스크에 접근 권한이 없습니다."),

    EVALUATION_NOT_FOUND(HttpStatus.NOT_FOUND, 50001, "평가 데이터를 찾을 수 없습니다."),
    EVALUATION_ALREADY_EXISTS(HttpStatus.CONFLICT, 50002, "해당 날짜의 평가가 이미 존재합니다."),

    FRIEND_NOT_FOUND(HttpStatus.NOT_FOUND, 60001, "친구 관계를 찾을 수 없습니다."),
    FRIEND_ALREADY_EXISTS(HttpStatus.CONFLICT, 60002, "이미 친구 요청을 보냈습니다."),

    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, 70001, "파일을 찾을 수 없습니다."),
    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, 70002, "파일 업로드에 실패했습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 99999, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
