create sequence TEMPORARY_USER_SEQ increment by 1;

CREATE TABLE IF NOT EXISTS TEMPORARY_USER (
    id bigint PRIMARY KEY,
    uuid VARCHAR(127) not null,
    name VARCHAR(1024) not null,
    silly_password VARCHAR(1024) default 'NO_PASSWORD_NEEDED',
    room_id INTEGER not null,

    CONSTRAINT fk_temporary_user_room_id foreign key (room_id) references ROOM(id),
);

create sequence QUESTION_ANSWER_SEQ increment by 1;

CREATE TABLE IF NOT EXISTS QUESTION_ANSWER (
    id bigint PRIMARY KEY,
    user_answer INTEGER,
    question_id INTEGER not null,
    temporary_user_id INTEGER not null,

    CONSTRAINT fk_question_answer_room_id foreign key (temporary_user_id) references TEMPORARY_USER(id),
    CONSTRAINT fk_question_answer_question_id foreign key (question_id) references QUESTION(id),
)