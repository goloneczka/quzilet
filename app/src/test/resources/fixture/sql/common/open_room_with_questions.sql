ALTER SEQUENCE ROOM_SEQ RESTART WITH 1;
ALTER SEQUENCE QUESTION_SEQ RESTART WITH 1;
ALTER SEQUENCE TEMPORARY_USER_SEQ RESTART WITH 1;

SET @room_id = ROOM_SEQ.nextval;
SET @st_question_id = QUESTION_SEQ.nextval;
SET @nd_question_id = QUESTION_SEQ.nextval;
SET @th_question_id = QUESTION_SEQ.nextval;

insert into ROOM values
        (@room_id, '111-222', 'test room', DATEADD('DAY', -1, CURRENT_DATE), DATEADD('DAY', 1, CURRENT_DATE));


insert into QUESTION values
    (@th_question_id, '111-222-C', 'test question 3', 'ask1', 'ask2', 'ask3', 'ask4', 1, @room_id, null);

insert into QUESTION values
    (@nd_question_id, '111-222-B', 'test question 2', 'ask1', 'ask2', 'ask3', 'ask4', 1, @room_id, @th_question_id);

insert into QUESTION values
    (@st_question_id, '111-222-A', 'test question 1', 'ask1', 'ask2', 'ask3', 'ask4', 1, @room_id, @nd_question_id);


insert into TEMPORARY_USER (id, uuid, name, silly_password, room_id)
values (TEMPORARY_USER_SEQ.nextval, '1111-222-3333-AA', 'TestName', 'NO_PASSWORD_NEEDED', @room_id);
