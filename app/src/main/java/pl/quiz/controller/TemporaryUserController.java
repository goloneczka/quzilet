package pl.quiz.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.quiz.ControllerMapping;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.service.TemporaryUserService;


@RestController
@AllArgsConstructor
public class TemporaryUserController {

    public final TemporaryUserService temporaryUserService;

    @PostMapping(value = ControllerMapping.CREATE_TMP_USER)
    ResponseEntity<String> createNewTempUser(@RequestBody TemporaryUserDto temporaryUser){
        return ResponseEntity.ok()
                .body(temporaryUserService.createTempUser(temporaryUser));
    }
}
