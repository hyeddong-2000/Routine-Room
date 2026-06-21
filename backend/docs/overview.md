# Routine Room - Backend 프로젝트 개요

## 1. 프로젝트 소개

**루틴룸(Routine Room)**은 단순한 투두리스트를 넘어, AI 분석과 실시간 소통이 결합된 **소셜 갓생 플랫폼**이다.

과거 싸이월드·블로그가 주던 아늑한 개인 공간의 감성(BGM, 실시간 채팅, 비밀글·댓글)에 현대적인 데이터 기반 루틴 관리(칸반 대시보드, 차트 시각화, AI 코칭)를 결합한 서비스.

## 2. 핵심 기능

| 기능 | 설명 |
|------|------|
| 멀티 캘린더 & 대시보드 | 데일리(하루 루틴), 위클리(칸반 보드), 먼슬리(한 달 흐름)가 유기적으로 연결 |
| 다각도 루틴 평가 | 일, 공부, 운동, 식단, 물 섭취량, 영양제 등 카테고리별 매일 점수 매기기 |
| Spring AI 갓생 컨설팅 | 일주일 통계 데이터 기반 AI 주간 리포트 (장점·보완점 분석) |
| 실시간 채팅 & 응원 | WebSocket(STOMP) 기반 실시간 채팅방, 친구 달력 방문 후 응원 스티커 남기기 |
| 공개/비공개 글 권한 | Spring Security 기반 공개·비공개·친구공개 권한 분리 |

## 3. 기술 스택

### Core
- **Java 21 LTS** + **Spring Boot 3.x**
- **MyBatis** (ORM) - Mapper XML + Annotation 방식 혼용
- **Java 21 가상 스레드(Virtual Threads)** 활성화 - 대량 소켓 연결 시 부하 없이 비동기 처리

### 보안 & 실시간
- **Spring Security** + **JWT** - 인증/인가, 공개/비공개 글 권한 분리
- **Spring WebSocket (STOMP)** - 실시간 채팅 및 토스트 알림 패킷 송수신

### AI
- **Spring AI** - 사용자 통계 JSON 데이터를 LLM 프롬프트에 매핑하여 맞춤 주간 리포트 생성

### 데이터베이스 & 인프라
- **PostgreSQL** (메인 RDB)
- **Redis** - 공통코드 캐싱, 세션 관리
- **Supabase Storage** (S3 호환) - 사진 업로드 저장소
- **Render / Railway** - 무료 호스팅 티어

### 설계 도구
- **ERDCloud** - DB 설계 후 DDL 자동 생성으로 테이블 생성

## 4. 패키지 구조

```
com.routineroom
├── common
│   ├── annotation      # 커스텀 어노테이션 (@Contains, @MultiCheck 등)
│   ├── code            # 공통코드 Redis 캐싱 (CommonCode)
│   ├── config          # Spring Security, WebSocket, Redis 설정
│   ├── filter          # JWT 인증 필터
│   ├── interceptor     # 로깅, 권한 체크 인터셉터
│   ├── security        # UserAuthenticationDTO, SecurityUtils
│   └── util            # 공통 유틸리티
├── controller          # 도메인별 컨트롤러 (user, routine, chat, ...)
├── service             # 도메인별 서비스
├── mapper              # MyBatis 매퍼 인터페이스
├── dto                 # Inner Class 패턴 DTO (RequestDTO, ResponseDTO)
└── entity              # BaseEntity 상속 엔티티
```

## 5. 공통 인프라 클래스

### BaseEntity
모든 엔티티의 부모 클래스. 등록자/등록일/수정자/수정일 공통 관리.

### BusinessException
의도적으로 던지는 비즈니스 커스텀 예외. `RuntimeException` 하위.

### ErrorCode (Enum)
```java
@Getter @AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "INVALID_INPUT_VALUE", "유효하지 않은 요청입니다."),
    TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "TASK_NOT_FOUND", "요청하신 할 일 정보를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
```

### GlobalExceptionHandler
`@RestControllerAdvice`로 모든 컨트롤러 예외를 최종 인터셉트하여 공통 응답 구조체로 변환.

### CommonCode
앱 시작 시 DB 공통코드를 Redis에 로드. `@DependsOn("redisIO")`로 Redis 빈 초기화 후 등록.

## 6. 개발 목표

- Java 21 가상 스레드 환경에서 RDB CRUD와 WebSocket 인프라가 상호 간섭 없이 작동하는 고성능 구조 검증
- 사용자 정량 데이터(수치) + 정성 데이터(반성문, 일기)를 LLM 연동하여 실질적인 라이프 피드백 시스템 구축
- ERDCloud로 M:N 관계를 매핑 테이블로 해소하는 체계적인 RDB 모델링
