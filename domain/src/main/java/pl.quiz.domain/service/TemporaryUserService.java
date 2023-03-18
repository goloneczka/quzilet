package pl.quiz.domain.service;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.port.TemporaryUserPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;

import java.util.List;

@AllArgsConstructor
public class TemporaryUserService {

    private final TemporaryUserPersistencePort temporaryUserPersistencePort;
    private final ValidatorUtil validator;

    public String createTempUser(TemporaryUserDto temporaryUser) {
        validator.checkValidation(temporaryUser);
        return temporaryUserPersistencePort.create(temporaryUser);
    }

    public TemporaryUserDto getTempUser(String uuid) {
        return temporaryUserPersistencePort.get(uuid);
    }

    public Long deleteTempUser(TemporaryUserDto dto) {
        return temporaryUserPersistencePort.delete(dto);
    }

    public boolean isTempUserExist(String uuid) {
        return temporaryUserPersistencePort.isExist(uuid);
    }

    public List<TemporaryUserDto> getTempUserInRoom(Long roomId){
        return temporaryUserPersistencePort.getTempUsersInRoom(roomId);
    }
}
