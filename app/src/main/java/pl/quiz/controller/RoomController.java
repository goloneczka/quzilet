package pl.quiz.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.quiz.domain.service.RoomService;
import pl.quiz.domain.dto.RoomDto;

import static pl.quiz.ControllerMapping.CREATE_ROOM;

@RestController
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = CREATE_ROOM)
    ResponseEntity<Long> createNewRoom(@RequestBody RoomDto room){
        return ResponseEntity.ok()
                .body(roomService.createRoom(room));
    }
}
