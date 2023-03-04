package pl.quiz;

public class ControllerMapping {

    public static final String CREATE_ROOM = "/room";

    public static final String OPEN_ROOM = "/room/start/{roomId}";
    public static final String SAVE_QUESTION_ANSWER = "/room/question-answer";
    public static final String GET_NEXT_QUESTION = "/room/question/{currentQuestionId}";
    public static final String GET_FINISH_DATA_TO_TMP_USER = "/temp-user/{userId}";

    public static final String CREATE_TMP_USER = "/temp-user";
}
