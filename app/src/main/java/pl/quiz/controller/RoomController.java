package pl.quiz.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.quiz.domain.RoomService;
import pl.quiz.domain.dto.RoomDto;

@RestController
@RequestMapping(path = "/room")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = "")
    ResponseEntity<Long> createNewRoom(@RequestBody RoomDto room){
        return ResponseEntity.ok()
                .body(roomService.createRoom(room));
    }
}
