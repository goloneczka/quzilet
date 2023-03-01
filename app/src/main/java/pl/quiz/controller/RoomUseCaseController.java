package pl.quiz.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.quiz.domain.RoomService;
import pl.quiz.domain.dto.RoomDto;

@RestController
@RequestMapping(path = "/room/start")
@AllArgsConstructor
public class RoomUseCaseController {

    private final RoomService roomService;

    @PostMapping(value = "")
    ResponseEntity<Long> startRoomQuiz(@RequestBody RoomDto room){
        return ResponseEntity.ok()
                .body(roomService.openRoomWithQuizIfPossible(room));
    }
}
