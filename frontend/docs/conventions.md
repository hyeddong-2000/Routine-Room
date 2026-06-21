# Routine Room - Frontend 개발 컨벤션 (FSD 아키텍처)

## 1. 아키텍처: Feature-Sliced Design (FSD)

카카오페이 사례를 참고하여 FSD(Feature-Sliced Design) 아키텍처를 채택한다.
코드의 재사용 범위와 단방향 의존성 규칙을 구조적으로 강제하여 응집도↑ 결합도↓를 유지한다.

### 핵심 규칙
1. **단방향 의존성**: `app → pages → widgets → features → entities → shared` 방향으로만 import 가능. 역방향 금지.
2. **같은 레이어 내 슬라이스 간 참조 금지**: 같은 레이어 안에서 다른 슬라이스를 import할 수 없다.
3. **세그먼트 이름은 목적 기준**: `components`, `hooks` 같은 성격 기반이 아닌 `ui`, `api`, `model` 같은 목적 기반 이름 사용.

---

## 2. 폴더 구조

```
src
├── app                         # 앱 전체 설정 (slice 없이 segment만)
│   ├── routes                  # 라우터 설정
│   ├── providers               # 전역 Provider (QueryClient, BrowserRouter 등)
│   └── styles                  # 전역 스타일, Tailwind 설정 import
│
├── pages                       # 페이지 단위 슬라이스 (라우팅 단위)
│   ├── dashboard               # slice group (동일 도메인 묶기 허용)
│   │   ├── daily               # slice - 데일리 대시보드 페이지
│   │   │   └── ui
│   │   ├── weekly              # slice - 위클리 칸반 페이지
│   │   │   └── ui
│   │   └── monthly             # slice - 먼슬리 달력 페이지
│   │       └── ui
│   ├── room                    # slice group - 내 루틴룸
│   │   └── ui
│   ├── chat                    # slice group - 실시간 채팅
│   │   └── ui
│   └── auth                    # slice group - 로그인/회원가입
│       └── ui
│
├── widgets                     # 여러 페이지에서 재사용되는 독립적 대형 UI 블록
│   ├── taskCard                # 할 일 카드 (대시보드, 칸반 공통)
│   │   └── ui
│   └── evaluationPanel         # 루틴 평가 패널
│       └── ui
│
├── features                    # 재사용되는 기능 단위 (동사적 - 사용자 행위 중심)
│   ├── createTask              # 할 일 생성
│   │   ├── ui                  # 생성 폼 컴포넌트
│   │   ├── api                 # 생성 API 요청 함수
│   │   └── model               # 폼 상태, 검증 스키마
│   ├── evaluateRoutine         # 루틴 평가 (점수 입력)
│   │   ├── ui
│   │   ├── api
│   │   └── model
│   ├── sendChatMessage         # 채팅 메시지 전송
│   │   ├── ui
│   │   └── api
│   └── authUser                # 로그인/로그아웃/회원가입
│       ├── ui
│       ├── api
│       └── model
│
├── entities                    # 비즈니스 데이터 (명사적 - 도메인 중심)
│   ├── tasks                   # 할 일 도메인
│   │   ├── ui                  # 여러 곳에서 재사용하는 Task 관련 UI
│   │   ├── api                 # Task 조회 API (여러 페이지에서 사용)
│   │   └── model               # Task 타입, 스키마
│   ├── routines                # 루틴 도메인
│   │   ├── ui
│   │   ├── api
│   │   └── model
│   ├── users                   # 유저 도메인
│   │   ├── ui
│   │   ├── api
│   │   └── model
│   ├── evaluations             # 평가 도메인
│   │   ├── ui
│   │   ├── api
│   │   └── model
│   └── chats                   # 채팅 도메인
│       ├── ui
│       ├── api
│       └── model
│
└── shared                      # 앱 기본 구성 요소 (비즈니스 로직 분리, slice 없이 segment만)
    ├── api                     # axios 인스턴스, 공통 API 응답 타입
    ├── ui                      # 비즈니스 무관 공통 컴포넌트 (Button, Modal, Input 등)
    ├── lib                     # 유틸리티, 헬퍼 함수
    ├── config                  # 환경변수, 상수
    └── types                   # 공통 TypeScript 타입
```

---

## 3. API 코드 위치 결정 기준

| 재사용 범위 | 위치 | 예시 |
|------------|------|------|
| 특정 페이지 슬라이스에서만 사용 | `pages/[slice-group]/[slice]/api` | 데일리 대시보드 전용 조회 |
| 특정 페이지 그룹(slice group) 내에서만 재사용 | `features/[feature]/api` | 할 일 생성 API (dashboard 내 여러 페이지에서 사용) |
| 여러 페이지 그룹에서 재사용 | `entities/[domain]/api` | Task 목록 조회 (dashboard, room 등 여러 곳에서 사용) |
| 전역 공통 설정 | `shared/api` | axios 인스턴스, ApiResponse 타입 |

### 상향식(Bottom-Up) 이동 원칙
1. 처음에는 `pages/[slice-group]/[slice]/` 하위에 작성한다.
2. 같은 slice group 내 다른 slice에서도 쓰이면 → `features/[feature]/` 로 이동.
3. 여러 slice group에서도 쓰이면 → `entities/[domain]/` 로 이동.
4. 전역에서 쓰이면 → `shared/` 로 이동.

---

## 4. 상태 관리

| 상태 종류 | 도구 | 위치 |
|-----------|------|------|
| 전역 인증 상태 | **Zustand** | `app/providers` or `entities/users/model` |
| BGM 오디오 상태 | **Zustand** | `shared/lib` or `app/providers` |
| 서버 상태 (API 캐싱) | **TanStack Query (v5)** | `entities/[domain]/api` or `features/[feature]/api` |
| 로컬 UI 상태 | `useState` | 각 컴포넌트 내 |

---

## 5. 파일 명명 규칙

| 종류 | 케이스 | 예시 |
|------|--------|------|
| UI 컴포넌트 파일 | PascalCase | `TaskCard.tsx`, `EvaluationForm.tsx` |
| API 함수 파일 | camelCase | `taskApi.ts`, `routineApi.ts` |
| 모델(타입/스토어) 파일 | camelCase | `task.types.ts`, `useTaskStore.ts` |
| 슬라이스 폴더명 | camelCase | `createTask/`, `evaluateRoutine/` |
| 엔티티 폴더명 | 복수형 | `tasks/`, `routines/`, `users/` |

---

## 6. API 응답 타입 (shared/types)

백엔드 공통 응답 포맷에 맞춰 `shared/types`에 정의한다.

```typescript
interface ApiResponse<T> {
  success: boolean;
  data: T | null;
  error: ApiError | null;
}

interface ApiError {
  code: string;
  message: string;
  errors?: FieldError[];
}

interface FieldError {
  field: string;
  value: string;
  reason: string;
}
```

---

## 7. 에러 메시지 처리 규칙

**프론트에서 에러 메시지를 직접 작성하지 않는다.** 백엔드 응답의 `error.message`를 그대로 표시한다.

```typescript
// 금지 (X)
if (!title) setError("제목을 입력해주세요.");

// 올바른 방법 (O)
const handleError = (error: ApiError) => {
  toast.error(error.message);
};
```

---

## 8. DTO 타입 정의 (백엔드 Inner Class 패턴 대응)

백엔드의 DTO Inner Class 패턴에 맞게 TypeScript namespace로 그룹화한다.

```typescript
// entities/tasks/model/task.types.ts
export namespace TaskRequestDTO {
  export interface Create {
    title: string;
    content: string;
    scope: string;
    targetDate: string;
  }
  export interface UpdateStatus {
    status: string;
  }
}

export namespace TaskResponseDTO {
  export interface Detail {
    id: number;
    title: string;
    content: string;
    status: string;
    isPublic: boolean;
    createdAt: string;
  }
  export interface SimpleInfo {
    id: number;
    title: string;
    status: string;
  }
}
```

---

## 9. 반응형 처리 원칙

- **웹 반응형만 진행한다.** React Native, Flutter, Android Studio 사용 없음.
- TailwindCSS 반응형 접두사(`sm:`, `md:`, `lg:`, `xl:`)로 처리.
- **모바일 우선(Mobile-first)**: 기본 스타일은 모바일, 큰 화면은 접두사로 확장.

```tsx
<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
```

---

## 10. 금지 규칙

- `any` 타입 사용 금지 (`unknown` 또는 명시 타입 사용)
- `Map`, `Object` 반환 타입 금지 - 명시된 인터페이스 사용
- 에러 메시지 프론트 직접 작성 금지 - 백엔드 응답 메시지 사용
- 인라인 스타일(`style={{}}`) 남발 금지 - TailwindCSS 클래스 사용
- 레이어 간 역방향 import 금지 (하위 레이어가 상위 레이어 import 불가)
- 같은 레이어 내 다른 슬라이스 import 금지
