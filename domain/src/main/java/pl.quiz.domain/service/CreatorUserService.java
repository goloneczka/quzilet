package pl.quiz.domain.service;


import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.CreatorUser;
import pl.quiz.domain.port.CreatorUserPersistencePort;

@AllArgsConstructor
public class CreatorUserService {

    private final CreatorUserPersistencePort creatorUserPersistencePort;

    public CreatorUser getCreatorUser(String name){
        return creatorUserPersistencePort.get(name);
    }
}
