CREATE TABLE IF NOT EXISTS ROOM (
    id SERIAL PRIMARY KEY,
    uuid_path VARCHAR(127) not null unique,
    name VARCHAR(1024) not null,
);

CREATE TABLE IF NOT EXISTS QUESTION (
    id SERIAL PRIMARY KEY,
    uuid_path VARCHAR(127) not null unique,
    name VARCHAR(1024) not null,
    ask VARCHAR(127) not null,
    ask2 VARCHAR(127) not null,
    ask3 VARCHAR(127),
    ask4 VARCHAR(127),
    correct_answ_number INTEGER not null,
    room_id INTEGER not null,
    next_question_id INTEGER,

    CONSTRAINT fk_room_id foreign key (room_id) references ROOM(id),
    CONSTRAINT fk_question_id foreign key (next_question_id) references QUESTION(id),

    );


