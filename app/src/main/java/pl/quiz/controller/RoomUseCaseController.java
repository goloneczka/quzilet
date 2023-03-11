package pl.quiz.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.quiz.domain.dto.QuestionAnswerDto;
import pl.quiz.domain.dto.QuestionToAskDto;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;
import pl.quiz.domain.service.QuestionAnswerService;
import pl.quiz.domain.service.QuestionService;
import pl.quiz.domain.service.RoomService;
import pl.quiz.domain.dto.vo.OpenRoomVO;

import java.time.LocalDateTime;
import java.util.List;

import static pl.quiz.ControllerMapping.*;

@RestController
@AllArgsConstructor
public class RoomUseCaseController {

    private final RoomService roomService;
    private final QuestionService questionService;
    private final QuestionAnswerService questionAnswerService;

    @PostMapping(value = OPEN_ROOM)
    ResponseEntity<QuestionToAskDto> startRoomQuiz(@PathVariable Long roomId) {
        return ResponseEntity.ok()
                .body(
                        roomService.openRoomWithQuizIfPossible(
                                new OpenRoomVO(roomId, LocalDateTime.now())
                        )
                );
    }

    @PostMapping(value = SAVE_QUESTION_ANSWER)
    ResponseEntity<Long> saveAnswer(@RequestBody QuestionAnswerDto questionAnswerDto, Authentication auth) {
        return ResponseEntity.ok()
                .body(
                        questionAnswerService.createQuestionAnswer(questionAnswerDto, auth.getName())
                );
    }

    @GetMapping(value = GET_NEXT_QUESTION)
    ResponseEntity<QuestionToAskDto> getNextQuestion(@PathVariable Long currentQuestionId) {
        return ResponseEntity.ok()
                .body(
                        questionService.getNextQuestion(currentQuestionId)
                );
    }

    @GetMapping(value = GET_FINISH_DATA_TO_TMP_USER)
    ResponseEntity<List<TempUserFinishDataVO>> getFinishTempUserData(Authentication auth) {
        return ResponseEntity.ok()
                .body(
                        questionAnswerService.getTempUserScore(auth.getName())
                );
    }

}
