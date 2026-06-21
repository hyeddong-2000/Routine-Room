-- ============================================================
-- Routine Room Schema DDL (PostgreSQL)
-- ============================================================

-- 공통코드 테이블 (Redis 캐싱 기반 - 앱 시작 시 로드)
CREATE TABLE IF NOT EXISTS tb_common_code (
    code        VARCHAR(30)  NOT NULL,
    code_grp    VARCHAR(30)  NOT NULL,
    code_nm     VARCHAR(100) NOT NULL,
    sort_ord    INTEGER      NOT NULL DEFAULT 0,
    use_yn      CHAR(1)      NOT NULL DEFAULT 'Y',
    rgtr        VARCHAR(50),
    reg_dt      TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_mdfr   VARCHAR(50),
    last_mdfcn_dt TIMESTAMP,
    PRIMARY KEY (code_grp, code)
);

-- 파일 관리 테이블
CREATE TABLE IF NOT EXISTS tb_file_mng (
    file_id       SERIAL       PRIMARY KEY,
    file_path     VARCHAR(500) NOT NULL,
    orgn_file_nm  VARCHAR(255) NOT NULL,
    chg_file_nm   VARCHAR(255) NOT NULL,
    file_ext      VARCHAR(20)  NOT NULL,
    file_size     BIGINT       NOT NULL DEFAULT 0,
    rgtr          VARCHAR(50),
    reg_dt        TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_mdfr     VARCHAR(50),
    last_mdfcn_dt TIMESTAMP
);

-- 유저 테이블
CREATE TABLE IF NOT EXISTS tb_users (
    user_no          SERIAL       PRIMARY KEY,
    id               VARCHAR(50)  NOT NULL UNIQUE,
    password         VARCHAR(255) NOT NULL,
    nickname         VARCHAR(50)  NOT NULL UNIQUE,
    authority        VARCHAR(20)  NOT NULL DEFAULT 'ROLE_USER',
    bgm_file_id      INTEGER      REFERENCES tb_file_mng(file_id),
    bgm_use_yn       CHAR(1)      NOT NULL DEFAULT 'N',
    profile_file_id  INTEGER      REFERENCES tb_file_mng(file_id),
    today_cnt        INTEGER      NOT NULL DEFAULT 0,
    total_cnt        INTEGER      NOT NULL DEFAULT 0,
    client_ip        VARCHAR(45),
    rgtr             VARCHAR(50),
    reg_dt           TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_mdfr        VARCHAR(50),
    last_mdfcn_dt    TIMESTAMP
);

-- 유저 디바이스 테이블 (알림용)
CREATE TABLE IF NOT EXISTS tb_user_devices (
    device_id     SERIAL       PRIMARY KEY,
    user_no       INTEGER      NOT NULL REFERENCES tb_users(user_no),
    device_token  VARCHAR(500) NOT NULL,
    os_type       VARCHAR(10)  NOT NULL,  -- code_grp: OS_TYPE
    rgtr          VARCHAR(50),
    reg_dt        TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_mdfr     VARCHAR(50),
    last_mdfcn_dt TIMESTAMP
);

-- 루틴 테이블 (반복 루틴 정의)
CREATE TABLE IF NOT EXISTS tb_routines (
    routine_id    SERIAL       PRIMARY KEY,
    user_no       INTEGER      NOT NULL REFERENCES tb_users(user_no),
    title         VARCHAR(100) NOT NULL,
    content       TEXT,
    category_cd   VARCHAR(30)  NOT NULL,  -- code_grp: ROUTINE_CATEGORY
    cron_expr     VARCHAR(50),            -- ex) "0 0 * * *" (매일)
    is_active     BOOLEAN      NOT NULL DEFAULT TRUE,
    rgtr          VARCHAR(50),
    reg_dt        TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_mdfr     VARCHAR(50),
    last_mdfcn_dt TIMESTAMP
);

-- 할 일 테이블 (칸반 보드 단위)
CREATE TABLE IF NOT EXISTS tb_tasks (
    task_id       BIGSERIAL    PRIMARY KEY,
    routine_id    BIGINT       REFERENCES tb_routines(routine_id),
    title         VARCHAR(200) NOT NULL,
    content       TEXT,
    status_cd     VARCHAR(20)  NOT NULL DEFAULT 'TODO',  -- code_grp: TASK_STATUS
    priority_cd   VARCHAR(20)  NOT NULL DEFAULT 'MEDIUM', -- code_grp: TASK_PRIORITY
    due_dt        DATE,
    assignee_id   BIGINT       REFERENCES tb_users(user_no),
    order_seq     INTEGER      NOT NULL DEFAULT 0,
    rgtr          VARCHAR(50),
    reg_dt        TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_mdfr     VARCHAR(50),
    last_mdfcn_dt TIMESTAMP
);

-- 루틴 일별 수행 내역 (달력 체크)
CREATE TABLE IF NOT EXISTS tb_routine_todo_dtl (
    todo_id       SERIAL   PRIMARY KEY,
    routine_id    INTEGER  NOT NULL REFERENCES tb_routines(routine_id),
    user_no       INTEGER  NOT NULL REFERENCES tb_users(user_no),
    target_date   DATE     NOT NULL,
    status_cd     VARCHAR(20) NOT NULL DEFAULT 'TODO',  -- code_grp: TASK_STATUS
    cert_file_id  INTEGER  REFERENCES tb_file_mng(file_id),
    rgtr          VARCHAR(50),
    reg_dt        TIMESTAMP NOT NULL DEFAULT NOW(),
    last_mdfr     VARCHAR(50),
    last_mdfcn_dt TIMESTAMP,
    UNIQUE (routine_id, user_no, target_date)
);

-- 일일 평가 테이블
CREATE TABLE IF NOT EXISTS tb_daily_evaluation (
    eval_id          SERIAL   PRIMARY KEY,
    user_no          INTEGER  NOT NULL REFERENCES tb_users(user_no),
    eval_date        DATE     NOT NULL,
    score_work       INTEGER  NOT NULL DEFAULT 0 CHECK (score_work BETWEEN 0 AND 10),
    score_study      INTEGER  NOT NULL DEFAULT 0 CHECK (score_study BETWEEN 0 AND 10),
    score_exercise   INTEGER  NOT NULL DEFAULT 0 CHECK (score_exercise BETWEEN 0 AND 10),
    score_diet       INTEGER  NOT NULL DEFAULT 0 CHECK (score_diet BETWEEN 0 AND 10),
    water_intake_ml  INTEGER  NOT NULL DEFAULT 0,
    taken_supplements BOOLEAN NOT NULL DEFAULT FALSE,
    daily_comment    TEXT,
    rgtr             VARCHAR(50),
    reg_dt           TIMESTAMP NOT NULL DEFAULT NOW(),
    last_mdfr        VARCHAR(50),
    last_mdfcn_dt    TIMESTAMP,
    UNIQUE (user_no, eval_date)
);

-- 채팅방 테이블
CREATE TABLE IF NOT EXISTS tb_chat_rooms (
    room_id       SERIAL       PRIMARY KEY,
    room_title    VARCHAR(100) NOT NULL,
    room_type_cd  VARCHAR(20)  NOT NULL,  -- code_grp: CHAT_ROOM_TYPE
    rgtr          VARCHAR(50),
    reg_dt        TIMESTAMP    NOT NULL DEFAULT NOW(),
    last_mdfr     VARCHAR(50),
    last_mdfcn_dt TIMESTAMP
);

-- 채팅방 멤버 테이블
CREATE TABLE IF NOT EXISTS tb_chat_room_members (
    room_id    INTEGER   NOT NULL REFERENCES tb_chat_rooms(room_id),
    user_no    INTEGER   NOT NULL REFERENCES tb_users(user_no),
    joined_dt  TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (room_id, user_no)
);

-- 채팅 메시지 테이블
CREATE TABLE IF NOT EXISTS tb_chat_messages (
    message_id      SERIAL   PRIMARY KEY,
    room_id         INTEGER  NOT NULL REFERENCES tb_chat_rooms(room_id),
    user_no         INTEGER  NOT NULL REFERENCES tb_users(user_no),
    message_content TEXT     NOT NULL,
    is_read         BOOLEAN  NOT NULL DEFAULT FALSE,
    rgtr            VARCHAR(50),
    reg_dt          TIMESTAMP NOT NULL DEFAULT NOW(),
    last_mdfr       VARCHAR(50),
    last_mdfcn_dt   TIMESTAMP
);

-- 친구 관계 테이블
CREATE TABLE IF NOT EXISTS tb_friends (
    friendship_id   SERIAL  PRIMARY KEY,
    requester_no    INTEGER NOT NULL REFERENCES tb_users(user_no),
    receiver_no     INTEGER NOT NULL REFERENCES tb_users(user_no),
    status_cd       VARCHAR(20) NOT NULL DEFAULT 'PENDING',  -- code_grp: FRIEND_STATUS
    rgtr            VARCHAR(50),
    reg_dt          TIMESTAMP NOT NULL DEFAULT NOW(),
    last_mdfr       VARCHAR(50),
    last_mdfcn_dt   TIMESTAMP,
    UNIQUE (requester_no, receiver_no)
);

-- 캘린더 응원 댓글 테이블 (싸이월드 방명록 감성)
CREATE TABLE IF NOT EXISTS tb_calendar_comments (
    comment_id        SERIAL   PRIMARY KEY,
    target_user_no    INTEGER  NOT NULL REFERENCES tb_users(user_no),
    writer_user_no    INTEGER  NOT NULL REFERENCES tb_users(user_no),
    target_date       DATE     NOT NULL,
    content           TEXT     NOT NULL,
    sticker_cd        VARCHAR(30),              -- code_grp: STICKER
    is_secret         BOOLEAN  NOT NULL DEFAULT FALSE,
    parent_comment_id INTEGER  REFERENCES tb_calendar_comments(comment_id),
    is_deleted        BOOLEAN  NOT NULL DEFAULT FALSE,
    rgtr              VARCHAR(50),
    reg_dt            TIMESTAMP NOT NULL DEFAULT NOW(),
    last_mdfr         VARCHAR(50),
    last_mdfcn_dt     TIMESTAMP
);

-- AI 주간 리포트 테이블
CREATE TABLE IF NOT EXISTS tb_ai_weekly_report (
    report_id           SERIAL  PRIMARY KEY,
    user_no             INTEGER NOT NULL REFERENCES tb_users(user_no),
    start_date          DATE    NOT NULL,
    end_date            DATE    NOT NULL,
    ai_feedback_content TEXT    NOT NULL,
    total_grade_cd      VARCHAR(10) NOT NULL,  -- code_grp: AI_GRADE
    rgtr                VARCHAR(50),
    reg_dt              TIMESTAMP NOT NULL DEFAULT NOW(),
    last_mdfr           VARCHAR(50),
    last_mdfcn_dt       TIMESTAMP,
    UNIQUE (user_no, start_date, end_date)
);

-- ============================================================
-- Indexes
-- ============================================================

CREATE INDEX IF NOT EXISTS idx_routines_user_no    ON tb_routines(user_no);
CREATE INDEX IF NOT EXISTS idx_tasks_routine_id    ON tb_tasks(routine_id);
CREATE INDEX IF NOT EXISTS idx_tasks_assignee_id   ON tb_tasks(assignee_id);
CREATE INDEX IF NOT EXISTS idx_todo_dtl_user_date  ON tb_routine_todo_dtl(user_no, target_date);
CREATE INDEX IF NOT EXISTS idx_daily_eval_user_date ON tb_daily_evaluation(user_no, eval_date);
CREATE INDEX IF NOT EXISTS idx_chat_msg_room_id    ON tb_chat_messages(room_id);
CREATE INDEX IF NOT EXISTS idx_friends_requester   ON tb_friends(requester_no);
CREATE INDEX IF NOT EXISTS idx_friends_receiver    ON tb_friends(receiver_no);
CREATE INDEX IF NOT EXISTS idx_cal_comment_target  ON tb_calendar_comments(target_user_no, target_date);
CREATE INDEX IF NOT EXISTS idx_ai_report_user_no   ON tb_ai_weekly_report(user_no);
