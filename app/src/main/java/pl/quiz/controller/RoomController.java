package pl.quiz.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.quiz.domain.dto.RoomDto;
import pl.quiz.domain.service.usecase.RoomServiceUseCase;

import static pl.quiz.ControllerMapping.CREATE_ROOM;

@RestController
@AllArgsConstructor
public class RoomController {

    private final RoomServiceUseCase roomServiceUseCase;

    @PostMapping(value = CREATE_ROOM)
    ResponseEntity<Long> createNewRoom(@RequestBody RoomDto room, Authentication authentication){
        return ResponseEntity.ok()
                .body(roomServiceUseCase.createRoom(room, authentication.getName()));
    }
}
