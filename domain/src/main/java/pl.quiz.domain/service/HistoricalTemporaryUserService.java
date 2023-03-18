package pl.quiz.domain.service;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.*;
import pl.quiz.domain.port.HistoricalTempUserPersistencePort;
import pl.quiz.domain.port.TemporaryUserPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;

@AllArgsConstructor
public class HistoricalTemporaryUserService {

    private final HistoricalTempUserPersistencePort historicalTempUserPersistencePort;
    private final RoomService roomService;
    private final ValidatorUtil validator;

    public Long createHistTempUser(HistoricalTempUserDto historicalTempUserDto) {
        validator.checkValidation(historicalTempUserDto);
        RoomDto roomDto = roomService.getRoom(historicalTempUserDto.getRoomId());
        return historicalTempUserPersistencePort.create(buildHistoricalTempUser(historicalTempUserDto, roomDto));
    }

    private HistoricalTempUser buildHistoricalTempUser(HistoricalTempUserDto dto, RoomDto roomDto) {
        return HistoricalTempUser.builder()
                .usernameForHistoricalTempUser(dto.getUsernameForHistoricalTempUser())
                .correctAnswers(dto.getCorrectAnswers())
                .room(roomDto)
                .build();
    }

}

