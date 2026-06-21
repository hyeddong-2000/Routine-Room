# Routine Room - Backend 개발 컨벤션

## 1. 패키지 명명 규칙

- **레이어 패키지명**: 단수형 (`controller`, `service`, `mapper`, `dto`, `entity`)
- **도메인 서브 패키지명**: 복수형 (`users`, `routines`, `tasks`, `evaluations`, `chats`, `friends`, `files`)

```
com.routineroom.controller.users
com.routineroom.controller.routines
com.routineroom.controller.tasks
com.routineroom.service.users
com.routineroom.service.routines
com.routineroom.mapper.users
com.routineroom.mapper.routines
com.routineroom.dto.users
com.routineroom.dto.routines
com.routineroom.entity.users
com.routineroom.entity.routines
```

## 2. 레이어별 메서드 명명 규칙

### Repository / Mapper Layer (MyBatis)
SQL 행위를 직접 반영한 명명을 사용한다.

| 용도 | 접두어 | 예시 |
|------|--------|------|
| 단건 조회 | `selectBy` | `selectById()`, `selectByEmail()` |
| 리스트 조회 | `selectListBy` / `selectPageBy` | `selectListByUserId()` |
| 저장 | `insert` | `insert()` |
| 수정 | `update` | `update()` |
| 존재 여부 | `selectCountBy` | `selectCountByEmail()` (0보다 크면 존재) |
| 삭제 | `deleteById` / `delete` | `deleteById()` |

### Service Layer
유저 행위(유즈케이스) 중심의 동사 명명. 존재하지 않으면 Exception 발생.

| 용도 | 접두어 | 예시 |
|------|--------|------|
| 조회 | `get` | `getRoutine(Long id)`, `getUserProfile(...)` |
| 생성 | `create` | `createTask(...)`, `createChatRoom(...)` |
| 수정 | `modify` / `update` | `modifyEvaluation(...)`, `updateStatus(...)` |
| 삭제 | `remove` / `leave` | `removeComment(...)`, `leaveChatRoom(...)` |

### Controller Layer
HTTP 메서드와 결합되어 흐름을 나타낸다.

| 용도 | 접두어 | 예시 |
|------|--------|------|
| 조회 | `get` | `getDashboard(...)`, `getCalendarList(...)` |
| 등록 | `add` / `register` | `addTask(...)`, `registerUser(...)` |
| 수정 | `update` | `updateEvaluation(...)` |
| 삭제 | `delete` | `deleteComment(...)` |

## 3. DTO 구조 규칙: Inner Class 패턴

DTO 파일 난립 방지를 위해 도메인 단위로 RequestDTO / ResponseDTO 파일을 하나씩 만들고, 내부에 Static Inner Class로 관리한다.

### RequestDTO 예시
```java
public class TaskRequestDTO {

    @Getter @NoArgsConstructor
    public static class Create {
        private String title;
        private String content;
        private String scope;
        private LocalDate targetDate;
    }

    @Getter @NoArgsConstructor
    public static class UpdateStatus {
        private String status;
    }

    @Getter @NoArgsConstructor
    public static class Modify {
        private String title;
        private String content;
    }
}
```

### ResponseDTO 예시
```java
public class TaskResponseDTO {

    @Getter @Builder
    public static class Detail {
        private Long id;
        private String title;
        private String content;
        private String status;
        private boolean isPublic;
        private LocalDateTime createdAt;
    }

    @Getter @Builder
    public static class SimpleInfo {
        private Long id;
        private String title;
        private String status;
    }
}
```

## 4. RESTful URI 설계 규칙

- URI는 **복수 명사**로 자원을 표현한다.
- 행위는 HTTP Method(GET, POST, PUT, PATCH, DELETE)로만 표현한다.
- 단어 구분은 **하이픈(-) 케이스**를 사용한다. (카멜케이스 금지)

| 행위 | Method | URI | 비고 |
|------|--------|-----|------|
| 할 일 생성 | POST | `/api/tasks` | URI에 행위 기술 금지 |
| 특정 할 일 조회 | GET | `/api/tasks/{taskId}` | 명사 + 식별자 |
| 할 일 상태 수정 | PATCH | `/api/tasks/{taskId}/status` | 리소스 일부 수정 |
| 특정 할 일 삭제 | DELETE | `/api/tasks/{taskId}` | |
| 댓글 등록 | POST | `/api/tasks/{taskId}/comments` | 계층적 자원 표현 |
| 채팅 메시지 조회 | GET | `/api/chat-rooms/{roomId}/messages` | 하이픈(-) 케이스 |

### URI 금지 규칙
```
/api/tasks/create-task       ← 행위 동사 포함 금지
/api/tasks/delete?id=1       ← Query Parameter로 행위 제어 금지
/api/chatRoom/               ← 카멜케이스 금지
```

### POST로 조회를 보내야 하는 예외 상황
조회 조건이 너무 많거나 민감 정보가 포함될 때는 POST 사용. 이 경우 명사형 목적어를 URI 끝에 붙여 생성(C) 요청과 구분한다.

```
POST /api/tasks/condition-filter    ← 조건 필터링 조회 (O)
POST /api/tasks/bulk-query          ← 대량 데이터 조건 조회 (O)
POST /api/tasks/search              ← 동사 사용 금지 (X)
```

## 5. 공통 응답 포맷

모든 API 응답은 아래 구조를 유지한다.

### 성공 응답
```json
{
  "success": true,
  "data": { },
  "error": null
}
```

### 실패 응답
```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "INVALID_INPUT_VALUE",
    "message": "물 섭취량은 음수일 수 없습니다."
  }
}
```

## 6. 예외 처리 컨벤션

### I/O 및 Checked Exception (예측·복구 가능한 예외)
- 대상: 파일 업로드/다운로드 실패, AI API 타임아웃, 외부 네트워크 단절, 직렬화/역직렬화 오류
- 처리: `try-catch`로 반드시 잡아내거나 비즈니스 커스텀 예외로 전환. 단순 로그만 남기지 말고 재시도 로직 또는 명확한 안내 메시지와 함께 상태 코드 반환.

### Runtime 및 Unchecked Exception (로직 에러)
- 대상: 비즈니스 규칙 위반, 유저 입력값 검증 실패
- 처리: `IllegalArgumentException`으로 때려박기 금지. 프로젝트 커스텀 `BusinessException`(RuntimeException 하위)을 던지고 전역 핸들러에서 처리.
- **컨트롤러 계층까지 예외를 끌고 오지 않는다.**

### 전역 예외 응답 포맷
```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "TASK_NOT_FOUND",
    "message": "요청하신 할 일 정보를 찾을 수 없습니다.",
    "errors": []
  }
}
```

### ErrorResponse 구조
```java
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private final boolean success = false;
    private final Object data = null;
    private ErrorDetail error;

    @Getter
    public static class ErrorDetail {
        private String code;
        private String message;
        private List<FieldError> errors;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public static List<FieldError> of(final BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()
                    ))
                    .collect(Collectors.toList());
        }
    }

    public static ErrorResponse of(final ErrorCode errorCode) { ... }
    public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) { ... }
}
```

### GlobalExceptionHandler 처리 순서
```java
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 1. @ModelAttribute 바인딩 / DTO 검증 실패
    @ExceptionHandler(BindException.class)

    // 2. @RequestBody @Valid 검증 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)

    // 3. 의도된 비즈니스 예외
    @ExceptionHandler(BusinessException.class)

    // 4. 제어 못한 시스템 예외 (최후 방어선)
    @ExceptionHandler(Exception.class)
}
```

## 7. 금지 규칙 (절대 하지 말 것)

- `Map`, `Object` 반환 타입 사용 금지 - 타입 명시된 DTO 사용
- 쿼리에 `SELECT *` 사용 금지 - 필요한 컬럼만 명시
- 와일드카드 남용 금지
- `LEFT JOIN` 남발 금지 - 필요한 JOIN만 사용
- 프론트에서 에러 메시지 직접 작성 금지 - 백엔드 응답 메시지 그대로 표시

## 8. 커스텀 어노테이션

### @Contains
특정 필드 값이 허용된 값 목록 중 하나인지 검증하는 커스텀 어노테이션.

```java
@Contains(acceptedValues = {"DAILY", "WEEKLY", "MONTHLY"}, message = "유효한 scope 값이 아닙니다.")
private String scope;
```

### @MultiCheck
휴대전화번호 형식, 주민번호 패턴, DB 금칙어 등을 검증하는 커스텀 어노테이션.

```java
@MultiCheck
private String nickname;
```

## 9. BaseEntity

모든 엔티티는 `BaseEntity`를 상속하여 등록자/등록일/수정자/수정일을 공통으로 관리한다.

```java
@Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
public class BaseEntity {
    protected String rgtr;
    protected LocalDateTime regDt;
    protected String lastMdfr;
    protected LocalDateTime lastMdfcnDt;

    public void setFirst(String objName) { ... }
    public void setLast(String objName) { ... }
}
```
