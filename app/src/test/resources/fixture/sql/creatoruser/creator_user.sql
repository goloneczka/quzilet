-- password -> 'password_to_encode!'

ALTER SEQUENCE CREATOR_USER_SEQ RESTART WITH 1;

SET @creator_id = CREATOR_USER_SEQ.nextval;

insert into CREATOR_USER values
    (@creator_id, 'Im_room_creator', '$2a$10$6VJnsUPHCDbjC4qOzo4Sd.b.J.i0xwJuktLDgbYQa13eascPMxpZa');