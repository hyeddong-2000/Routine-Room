\restrict 2cORVvHrWbyl9XIAjd97A5QRG2LLwzA3fPyAM2VIh243HsFyl4F87mFktCiYBdw
CREATE TABLE public.ai_weekly_report (
    report_id integer NOT NULL,
    user_no integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    ai_feedback_content text NOT NULL,
    total_grade_cd character varying(50),
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.ai_weekly_report_report_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.ai_weekly_report_report_id_seq OWNED BY public.ai_weekly_report.report_id;
CREATE TABLE public.calendar_comment (
    comment_id integer NOT NULL,
    target_user_no integer NOT NULL,
    writer_user_no integer NOT NULL,
    target_date date,
    content text NOT NULL,
    sticker_cd character varying(50),
    is_secret boolean DEFAULT false,
    parent_comment_id integer,
    is_deleted boolean DEFAULT false,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.calendar_comment_comment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.calendar_comment_comment_id_seq OWNED BY public.calendar_comment.comment_id;
CREATE TABLE public.chat_message (
    message_id integer NOT NULL,
    room_id integer NOT NULL,
    user_no integer NOT NULL,
    message_content text NOT NULL,
    is_read boolean DEFAULT false,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.chat_message_message_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.chat_message_message_id_seq OWNED BY public.chat_message.message_id;
CREATE TABLE public.chat_room (
    room_id integer NOT NULL,
    room_title character varying(100) NOT NULL,
    room_type_cd character varying(50) NOT NULL,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE public.chat_room_member (
    room_id integer NOT NULL,
    user_no integer NOT NULL,
    joined_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.chat_room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.chat_room_room_id_seq OWNED BY public.chat_room.room_id;
CREATE TABLE public.conf_code_set (
    code_cd character varying(50) NOT NULL,
    code_nm character varying(100) NOT NULL,
    use_yn character(1) DEFAULT 'Y'::bpchar,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE public.daily_evaluation (
    eval_id integer NOT NULL,
    user_no integer NOT NULL,
    eval_date date NOT NULL,
    score_work integer DEFAULT 0,
    score_study integer DEFAULT 0,
    score_exercise integer DEFAULT 0,
    score_diet integer DEFAULT 0,
    water_intake_ml integer DEFAULT 0,
    taken_supplements boolean DEFAULT false,
    daily_comment text,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.daily_evaluation_eval_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.daily_evaluation_eval_id_seq OWNED BY public.daily_evaluation.eval_id;
CREATE TABLE public.file_mng (
    file_id integer NOT NULL,
    file_path character varying(512) NOT NULL,
    orgn_file_nm character varying(255) NOT NULL,
    chg_file_nm character varying(255) NOT NULL,
    file_ext character varying(10) NOT NULL,
    file_size bigint NOT NULL,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.file_mng_file_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.file_mng_file_id_seq OWNED BY public.file_mng.file_id;
CREATE TABLE public.friend_mng (
    friend_ship_id integer NOT NULL,
    requester_no integer NOT NULL,
    receiver_no integer NOT NULL,
    status_cd character varying(50) NOT NULL,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.friend_mng_friend_ship_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.friend_mng_friend_ship_id_seq OWNED BY public.friend_mng.friend_ship_id;
CREATE TABLE public.routine_mng (
    routine_id integer NOT NULL,
    user_no integer NOT NULL,
    title character varying(100) NOT NULL,
    content text,
    category_cd character varying(50) NOT NULL,
    cron_expr character varying(50),
    is_active boolean DEFAULT true,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.routine_mng_routine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.routine_mng_routine_id_seq OWNED BY public.routine_mng.routine_id;
CREATE TABLE public.routine_todo_dtl (
    todo_id integer NOT NULL,
    routine_id integer NOT NULL,
    user_no integer NOT NULL,
    target_date date NOT NULL,
    status_cd character varying(50) NOT NULL,
    cert_file_id integer,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.routine_todo_dtl_todo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.routine_todo_dtl_todo_id_seq OWNED BY public.routine_todo_dtl.todo_id;
CREATE TABLE public.task_mng (
    task_id bigint NOT NULL,
    routine_id integer,
    user_no integer NOT NULL,
    title character varying(200) NOT NULL,
    content text,
    status_cd character varying(50) DEFAULT 'TASK_STATUS_TODO'::character varying NOT NULL,
    priority_cd character varying(50) DEFAULT 'TASK_PRIORITY_MEDIUM'::character varying NOT NULL,
    due_dt date,
    assignee_id bigint,
    order_seq integer DEFAULT 0 NOT NULL,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE SEQUENCE public.task_mng_task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.task_mng_task_id_seq OWNED BY public.task_mng.task_id;
CREATE TABLE public.user_device (
    device_id integer NOT NULL,
    user_no integer NOT NULL,
    device_token character varying(512) NOT NULL,
    os_type character varying(10) NOT NULL,
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.user_device_device_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.user_device_device_id_seq OWNED BY public.user_device.device_id;
CREATE TABLE public.user_mng (
    user_no integer NOT NULL,
    id character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    nickname character varying(50) NOT NULL,
    authority character varying(50) NOT NULL,
    bgm_file_id integer,
    bgm_use_yn character(1) DEFAULT 'Y'::bpchar,
    profile_file_id integer,
    today_cnt integer DEFAULT 0,
    total_cnt integer DEFAULT 0,
    client_ip character varying(45),
    rgtr character varying(50) NOT NULL,
    reg_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    last_mdfr character varying(50) NOT NULL,
    last_mdfcn_dt timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
CREATE SEQUENCE public.user_mng_user_no_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE public.user_mng_user_no_seq OWNED BY public.user_mng.user_no;
ALTER TABLE ONLY public.ai_weekly_report ALTER COLUMN report_id SET DEFAULT nextval('public.ai_weekly_report_report_id_seq'::regclass);
ALTER TABLE ONLY public.calendar_comment ALTER COLUMN comment_id SET DEFAULT nextval('public.calendar_comment_comment_id_seq'::regclass);
ALTER TABLE ONLY public.chat_message ALTER COLUMN message_id SET DEFAULT nextval('public.chat_message_message_id_seq'::regclass);
ALTER TABLE ONLY public.chat_room ALTER COLUMN room_id SET DEFAULT nextval('public.chat_room_room_id_seq'::regclass);
ALTER TABLE ONLY public.daily_evaluation ALTER COLUMN eval_id SET DEFAULT nextval('public.daily_evaluation_eval_id_seq'::regclass);
ALTER TABLE ONLY public.file_mng ALTER COLUMN file_id SET DEFAULT nextval('public.file_mng_file_id_seq'::regclass);
ALTER TABLE ONLY public.friend_mng ALTER COLUMN friend_ship_id SET DEFAULT nextval('public.friend_mng_friend_ship_id_seq'::regclass);
ALTER TABLE ONLY public.routine_mng ALTER COLUMN routine_id SET DEFAULT nextval('public.routine_mng_routine_id_seq'::regclass);
ALTER TABLE ONLY public.routine_todo_dtl ALTER COLUMN todo_id SET DEFAULT nextval('public.routine_todo_dtl_todo_id_seq'::regclass);
ALTER TABLE ONLY public.task_mng ALTER COLUMN task_id SET DEFAULT nextval('public.task_mng_task_id_seq'::regclass);
ALTER TABLE ONLY public.user_device ALTER COLUMN device_id SET DEFAULT nextval('public.user_device_device_id_seq'::regclass);
ALTER TABLE ONLY public.user_mng ALTER COLUMN user_no SET DEFAULT nextval('public.user_mng_user_no_seq'::regclass);
ALTER TABLE ONLY public.ai_weekly_report
    ADD CONSTRAINT ai_weekly_report_pkey PRIMARY KEY (report_id);
ALTER TABLE ONLY public.calendar_comment
    ADD CONSTRAINT calendar_comment_pkey PRIMARY KEY (comment_id);
ALTER TABLE ONLY public.chat_message
    ADD CONSTRAINT chat_message_pkey PRIMARY KEY (message_id);
ALTER TABLE ONLY public.chat_room_member
    ADD CONSTRAINT chat_room_member_pkey PRIMARY KEY (room_id, user_no);
ALTER TABLE ONLY public.chat_room
    ADD CONSTRAINT chat_room_pkey PRIMARY KEY (room_id);
ALTER TABLE ONLY public.conf_code_set
    ADD CONSTRAINT conf_code_set_pkey PRIMARY KEY (code_cd);
ALTER TABLE ONLY public.daily_evaluation
    ADD CONSTRAINT daily_evaluation_pkey PRIMARY KEY (eval_id);
ALTER TABLE ONLY public.file_mng
    ADD CONSTRAINT file_mng_pkey PRIMARY KEY (file_id);
ALTER TABLE ONLY public.friend_mng
    ADD CONSTRAINT friend_mng_pkey PRIMARY KEY (friend_ship_id);
ALTER TABLE ONLY public.routine_mng
    ADD CONSTRAINT routine_mng_pkey PRIMARY KEY (routine_id);
ALTER TABLE ONLY public.routine_todo_dtl
    ADD CONSTRAINT routine_todo_dtl_pkey PRIMARY KEY (todo_id);
ALTER TABLE ONLY public.task_mng
    ADD CONSTRAINT task_mng_pkey PRIMARY KEY (task_id);
ALTER TABLE ONLY public.friend_mng
    ADD CONSTRAINT uq_friend_pair UNIQUE (requester_no, receiver_no);
ALTER TABLE ONLY public.daily_evaluation
    ADD CONSTRAINT uq_user_eval_date UNIQUE (user_no, eval_date);
ALTER TABLE ONLY public.user_device
    ADD CONSTRAINT uq_user_token UNIQUE (user_no, device_token);
ALTER TABLE ONLY public.ai_weekly_report
    ADD CONSTRAINT uq_user_weekly UNIQUE (user_no, start_date);
ALTER TABLE ONLY public.user_device
    ADD CONSTRAINT user_device_pkey PRIMARY KEY (device_id);
ALTER TABLE ONLY public.user_mng
    ADD CONSTRAINT user_mng_id_key UNIQUE (id);
ALTER TABLE ONLY public.user_mng
    ADD CONSTRAINT user_mng_pkey PRIMARY KEY (user_no);
CREATE INDEX idx_chat_msg_room ON public.chat_message USING btree (room_id, reg_dt DESC);
CREATE INDEX idx_comment_target ON public.calendar_comment USING btree (target_user_no, target_date);
CREATE INDEX idx_eval_date ON public.daily_evaluation USING btree (user_no, eval_date);
CREATE INDEX idx_task_mng_status_cd ON public.task_mng USING btree (user_no, status_cd);
CREATE INDEX idx_task_mng_user_no ON public.task_mng USING btree (user_no);
CREATE INDEX idx_todo_date ON public.routine_todo_dtl USING btree (user_no, target_date);
CREATE UNIQUE INDEX uq_user_mng_nickname ON public.user_mng USING btree (nickname);
ALTER TABLE ONLY public.calendar_comment
    ADD CONSTRAINT fk_comm_parent FOREIGN KEY (parent_comment_id) REFERENCES public.calendar_comment(comment_id);
ALTER TABLE ONLY public.calendar_comment
    ADD CONSTRAINT fk_comm_target FOREIGN KEY (target_user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.calendar_comment
    ADD CONSTRAINT fk_comm_writer FOREIGN KEY (writer_user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.user_device
    ADD CONSTRAINT fk_device_user FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.daily_evaluation
    ADD CONSTRAINT fk_eval_user FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.friend_mng
    ADD CONSTRAINT fk_friend_rec FOREIGN KEY (receiver_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.friend_mng
    ADD CONSTRAINT fk_friend_req FOREIGN KEY (requester_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.chat_room_member
    ADD CONSTRAINT fk_member_room FOREIGN KEY (room_id) REFERENCES public.chat_room(room_id);
ALTER TABLE ONLY public.chat_room_member
    ADD CONSTRAINT fk_member_user FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.chat_message
    ADD CONSTRAINT fk_msg_room FOREIGN KEY (room_id) REFERENCES public.chat_room(room_id);
ALTER TABLE ONLY public.chat_message
    ADD CONSTRAINT fk_msg_user FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.ai_weekly_report
    ADD CONSTRAINT fk_report_user FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.routine_mng
    ADD CONSTRAINT fk_routine_user FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.routine_todo_dtl
    ADD CONSTRAINT fk_todo_file FOREIGN KEY (cert_file_id) REFERENCES public.file_mng(file_id);
ALTER TABLE ONLY public.routine_todo_dtl
    ADD CONSTRAINT fk_todo_routine FOREIGN KEY (routine_id) REFERENCES public.routine_mng(routine_id);
ALTER TABLE ONLY public.routine_todo_dtl
    ADD CONSTRAINT fk_todo_user FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.user_mng
    ADD CONSTRAINT fk_user_bgm FOREIGN KEY (bgm_file_id) REFERENCES public.file_mng(file_id);
ALTER TABLE ONLY public.user_mng
    ADD CONSTRAINT fk_user_profile FOREIGN KEY (profile_file_id) REFERENCES public.file_mng(file_id);
ALTER TABLE ONLY public.task_mng
    ADD CONSTRAINT task_mng_assignee_id_fkey FOREIGN KEY (assignee_id) REFERENCES public.user_mng(user_no);
ALTER TABLE ONLY public.task_mng
    ADD CONSTRAINT task_mng_routine_id_fkey FOREIGN KEY (routine_id) REFERENCES public.routine_mng(routine_id);
ALTER TABLE ONLY public.task_mng
    ADD CONSTRAINT task_mng_user_no_fkey FOREIGN KEY (user_no) REFERENCES public.user_mng(user_no);
\unrestrict 2cORVvHrWbyl9XIAjd97A5QRG2LLwzA3fPyAM2VIh243HsFyl4F87mFktCiYBdw
