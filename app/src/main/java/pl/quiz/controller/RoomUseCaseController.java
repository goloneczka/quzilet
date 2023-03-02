package pl.quiz.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.quiz.domain.service.RoomService;
import pl.quiz.domain.dto.OpenRoom;

import java.time.LocalDateTime;

import static pl.quiz.ControllerMapping.OPEN_ROOM;

@RestController
@AllArgsConstructor
public class RoomUseCaseController {

    private final RoomService roomService;

    @PostMapping(value = OPEN_ROOM)
    ResponseEntity<Long> startRoomQuiz(@PathVariable Long roomId){
        return ResponseEntity.ok()
                .body(
                        roomService.openRoomWithQuizIfPossible(
                                new OpenRoom(roomId, LocalDateTime.now())
                        )
                );
    }
}
