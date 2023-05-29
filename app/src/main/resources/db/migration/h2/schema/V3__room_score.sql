create sequence HISTORICAL_TEMP_USER_DATA_SEQ increment by 1;

CREATE TABLE IF NOT EXISTS HISTORICAL_TEMP_USER_DATA (
    id bigint PRIMARY KEY,
    room_id INTEGER not null,
    historical_temp_user_name VARCHAR(1024) not null,
    correct_answers int,

    CONSTRAINT fk_historical_temp_user_data_id foreign key (room_id) references ROOM(id),
);
