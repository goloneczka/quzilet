package pl.quiz.domain.service;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.port.TemporaryUserPersistencePort;
import pl.quiz.domain.validator.ValidatorUtil;

@AllArgsConstructor
public class TemporaryUserService {

    private final TemporaryUserPersistencePort temporaryUserPersistencePort;
    private final ValidatorUtil validator;

    public Long createTempUser(TemporaryUserDto temporaryUser) {
        validator.checkValidation(temporaryUser);
        return temporaryUserPersistencePort.create(temporaryUser);
    }

    public TemporaryUserDto getTempUser(String uuid) {
        return temporaryUserPersistencePort.get(uuid);
    }

    public boolean isTempUserExist(String uuid) {
        return temporaryUserPersistencePort.isExist(uuid);
    }
}
