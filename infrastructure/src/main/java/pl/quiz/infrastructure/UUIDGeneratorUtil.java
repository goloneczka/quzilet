package pl.quiz.infrastructure;

import java.util.UUID;

public class UUIDGeneratorUtil {

    public static String generate64StringUUID(){
        return UUID.randomUUID().toString();
    }
}
