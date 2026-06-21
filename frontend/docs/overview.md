# Routine Room - Frontend 프로젝트 개요

## 1. 프로젝트 소개

**루틴룸(Routine Room)**은 AI 분석 + 실시간 소통이 결합된 **소셜 갓생 플랫폼**이다.
싸이월드 감성(BGM, 실시간 채팅, 비밀글)에 현대적 루틴 관리(칸반 대시보드, 차트, AI 코칭)를 결합.

## 2. 핵심 화면 목록

| 화면 | 설명 |
|------|------|
| 데일리 대시보드 | 오늘의 루틴·할 일 현황, 물·영양제·운동 체크 |
| 위클리 칸반 | 이번 주 루틴 칸반 보드 |
| 먼슬리 캘린더 | 한 달 루틴 흐름 캘린더 |
| 루틴룸 (Room) | 싸이월드 감성 개인 공간 - BGM, 방문객 스티커, 비밀글 |
| 실시간 채팅 | WebSocket 기반 채팅방 |
| AI 주간 리포트 | Spring AI가 생성한 갓생 코칭 리포트 |
| 로그인/회원가입 | JWT 인증 |

## 3. 기술 스택

### Core
- **React** (Vite 빌드) + **TypeScript**
- **FSD(Feature-Sliced Design)** 아키텍처

### 상태 관리 & 데이터 페칭
- **Zustand**: 전역 클라이언트 상태 (인증 상태, BGM 오디오 상태)
- **TanStack Query v5**: 서버 상태 캐싱 및 동기화 (백엔드 API 데이터)

### 스타일링
- **TailwindCSS v4**: 반응형 처리 (`sm:`, `md:`, `lg:`, `xl:`)
- **모바일 우선(Mobile-first)** 반응형 설계

### 실시간
- **WebSocket (STOMP)** 클라이언트: 채팅, 토스트 알림

### 배포
- **Vercel** (무료 티어)

## 4. 반응형 전략

- **웹 반응형만 진행**. 별도 앱(Android Studio, Flutter, React Native) 개발 없음.
- Tailwind 반응형 브레이크포인트 기준:

| 브레이크포인트 | 사이즈 | 대상 디바이스 |
|---------------|--------|--------------|
| (기본값) | < 640px | 모바일 |
| `sm:` | 640px~ | 태블릿 소형 |
| `md:` | 768px~ | 태블릿 |
| `lg:` | 1024px~ | 데스크탑 소형 |
| `xl:` | 1280px~ | 데스크탑 |

## 5. 아키텍처: FSD 레이어 역할

| 레이어 | 역할 | 루틴룸 예시 |
|--------|------|------------|
| `app` | 전역 설정 (라우터, 프로바이더, 스타일) | QueryClientProvider, BrowserRouter, 전역 스타일 |
| `pages` | 라우팅 단위 페이지 | DailyDashboard, WeeklyKanban, Room, Chat |
| `widgets` | 여러 페이지 재사용 독립 UI 블록 | TaskCard, EvaluationPanel |
| `features` | 재사용 기능 단위 (동사적) | createTask, evaluateRoutine, sendChatMessage, authUser |
| `entities` | 비즈니스 데이터 (명사적) | tasks, routines, users, evaluations, chats |
| `shared` | 프로젝트 무관 공통 코드 | axios 인스턴스, Button/Input 컴포넌트, 유틸 함수 |

## 6. 환경 변수

```env
VITE_API_BASE_URL=         # 백엔드 API 서버 주소
VITE_WS_BASE_URL=          # WebSocket 서버 주소
VITE_SUPABASE_URL=         # Supabase Storage URL
VITE_SUPABASE_ANON_KEY=    # Supabase 인증 키
```
