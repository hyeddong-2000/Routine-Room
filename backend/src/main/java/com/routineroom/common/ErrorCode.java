package com.routineroom.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    TASK_NOT_FOUND("T001", "태스크를 찾을 수 없습니다."),
    TASK_ACCESS_DENIED("T002", "해당 태스크에 접근 권한이 없습니다."),
    TASK_ALREADY_COMPLETED("T003", "이미 완료된 태스크입니다."),

    USER_NOT_FOUND("U001", "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("U002", "이미 존재하는 사용자입니다."),

    UNAUTHORIZED("AUTH001", "인증이 필요합니다."),
    ACCESS_DENIED("AUTH002", "접근 권한이 없습니다."),

    INVALID_REQUEST("SYS001", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("SYS002", "서버 내부 오류가 발생했습니다.");

    private final String code;
    private final String message;
}
