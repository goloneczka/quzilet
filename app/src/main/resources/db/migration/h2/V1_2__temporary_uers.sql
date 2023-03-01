CREATE TABLE IF NOT EXISTS TEMPORARY_USER (
    id SERIAL PRIMARY KEY,
    name VARCHAR(1024) not null,
);

CREATE TABLE IF NOT EXISTS QUESTION_ANSWER (
    id SERIAL PRIMARY KEY,
    user_answer INTEGER not null,
    question_id INTEGER not null,
    temporary_user_id INTEGER not null,

    CONSTRAINT fk_room_id foreign key (temporary_user_id) references TEMPORARY_USER(id),
    CONSTRAINT fk_question_id foreign key (question_id) references QUESTION(id),
)