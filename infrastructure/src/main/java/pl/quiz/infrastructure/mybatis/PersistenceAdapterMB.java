package pl.quiz.infrastructure.mybatis;

import lombok.AllArgsConstructor;
import pl.quiz.domain.dto.vo.TempUserFinishDataVO;
import pl.quiz.domain.port.PersistencePortMB;

import java.util.List;

@AllArgsConstructor
public class PersistenceAdapterMB implements PersistencePortMB {

    private final FinishUserDataRepository finishUserDataRepository;

    public List<TempUserFinishDataVO> getUserFinishDataFromRoom(String userUuid){
        return finishUserDataRepository.getUserFinishDataFromRoom(userUuid);
    }
}
