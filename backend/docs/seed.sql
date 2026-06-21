-- ============================================================
-- Routine Room Seed Data (공통코드)
-- ============================================================

INSERT INTO tb_common_code (code_grp, code, code_nm, sort_ord) VALUES
-- 루틴 카테고리
('ROUTINE_CATEGORY', 'EXERCISE',   '운동',   1),
('ROUTINE_CATEGORY', 'STUDY',      '공부',   2),
('ROUTINE_CATEGORY', 'WORK',       '일',     3),
('ROUTINE_CATEGORY', 'DIET',       '식단',   4),
('ROUTINE_CATEGORY', 'HABIT',      '습관',   5),
('ROUTINE_CATEGORY', 'ETC',        '기타',   6),

-- 할 일 상태
('TASK_STATUS', 'TODO',        '예정',   1),
('TASK_STATUS', 'IN_PROGRESS', '진행중', 2),
('TASK_STATUS', 'DONE',        '완료',   3),
('TASK_STATUS', 'SKIP',        '건너뜀', 4),

-- 할 일 우선순위
('TASK_PRIORITY', 'LOW',    '낮음', 1),
('TASK_PRIORITY', 'MEDIUM', '보통', 2),
('TASK_PRIORITY', 'HIGH',   '높음', 3),

-- 채팅방 유형
('CHAT_ROOM_TYPE', 'PUBLIC',  '공개',   1),
('CHAT_ROOM_TYPE', 'PRIVATE', '비공개', 2),
('CHAT_ROOM_TYPE', 'FRIEND',  '친구',   3),

-- 친구 관계 상태
('FRIEND_STATUS', 'PENDING',  '대기중', 1),
('FRIEND_STATUS', 'ACCEPTED', '수락',   2),
('FRIEND_STATUS', 'REJECTED', '거절',   3),
('FRIEND_STATUS', 'BLOCKED',  '차단',   4),

-- AI 주간 리포트 등급
('AI_GRADE', 'S', 'S등급', 1),
('AI_GRADE', 'A', 'A등급', 2),
('AI_GRADE', 'B', 'B등급', 3),
('AI_GRADE', 'C', 'C등급', 4),
('AI_GRADE', 'D', 'D등급', 5),

-- 응원 스티커
('STICKER', 'FIRE',      '🔥 파이팅', 1),
('STICKER', 'CLAP',      '👏 수고해', 2),
('STICKER', 'HEART',     '❤️ 응원해', 3),
('STICKER', 'STAR',      '⭐ 최고야', 4),
('STICKER', 'THUMBS_UP', '👍 잘했어', 5),

-- 디바이스 OS 유형
('OS_TYPE', 'IOS',     'iOS',     1),
('OS_TYPE', 'ANDROID', 'Android', 2),
('OS_TYPE', 'WEB',     'Web',     3),

-- 권한
('AUTHORITY', 'ROLE_USER',  '일반 사용자', 1),
('AUTHORITY', 'ROLE_ADMIN', '관리자',     2)

ON CONFLICT (code_grp, code) DO NOTHING;


-- ============================================================
-- 테스트 유저 (개발 환경용 - bcrypt 인코딩된 "test1234")
-- ============================================================
INSERT INTO tb_users (id, password, nickname, authority, bgm_use_yn, today_cnt, total_cnt, rgtr, last_mdfr)
VALUES
  ('testuser1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8ioctGtaWFkBfXtS8SiX5X2b3JKRO', '혜똥', 'ROLE_USER', 'N', 0, 0, 'SYSTEM', 'SYSTEM'),
  ('testuser2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8ioctGtaWFkBfXtS8SiX5X2b3JKRO', '갓생러', 'ROLE_USER', 'N', 0, 0, 'SYSTEM', 'SYSTEM')
ON CONFLICT (id) DO NOTHING;


-- ============================================================
-- 샘플 루틴 (testuser1)
-- ============================================================
INSERT INTO tb_routines (user_no, title, content, category_cd, cron_expr, is_active, rgtr, last_mdfr)
SELECT
  u.user_no,
  r.title,
  r.content,
  r.category_cd,
  r.cron_expr,
  TRUE,
  'SYSTEM',
  'SYSTEM'
FROM tb_users u
CROSS JOIN (VALUES
  ('아침 30분 조깅',    '기상 후 조깅 30분 + 스트레칭 10분', 'EXERCISE', '0 6 * * *'),
  ('알고리즘 1문제',    '백준 또는 LeetCode 1문제 풀기',     'STUDY',    '0 21 * * *'),
  ('물 2리터 마시기',   '하루 최소 2000ml 수분 섭취',        'HABIT',    '0 8 * * *')
) AS r(title, content, category_cd, cron_expr)
WHERE u.id = 'testuser1'
ON CONFLICT DO NOTHING;


-- ============================================================
-- 샘플 할 일 (칸반 보드)
-- ============================================================
INSERT INTO tb_tasks (routine_id, title, content, status_cd, priority_cd, due_dt, order_seq, rgtr, last_mdfr)
SELECT
  ro.routine_id,
  t.title,
  t.content,
  t.status_cd,
  t.priority_cd,
  CURRENT_DATE + t.due_offset,
  t.order_seq,
  'SYSTEM',
  'SYSTEM'
FROM tb_routines ro
CROSS JOIN (VALUES
  ('Routine Room 프론트엔드 FSD 구조 세팅', '완료됨',     'DONE',        'HIGH',   -1, 1),
  ('Spring Security JWT 설정',              '진행중',     'IN_PROGRESS', 'HIGH',   3,  2),
  ('ERD 설계 완성',                         '내일까지',   'TODO',        'HIGH',   1,  3),
  ('대시보드 페이지 UI 구현',               '이번주',     'TODO',        'MEDIUM', 5,  4),
  ('AI 주간 리포트 프롬프트 설계',          '다음주',     'TODO',        'LOW',    7,  5)
) AS t(title, content, status_cd, priority_cd, due_offset, order_seq)
WHERE ro.routine_id = (SELECT MIN(routine_id) FROM tb_routines WHERE user_no = (SELECT user_no FROM tb_users WHERE id = 'testuser1'))
ON CONFLICT DO NOTHING;
