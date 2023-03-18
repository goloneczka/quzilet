package pl.quiz.domain.port;

import pl.quiz.domain.dto.vo.TempUserFinishDataVO;

import java.util.List;

public interface PersistencePortMB {

    List<TempUserFinishDataVO> getUserFinishDataFromRoom(String uuid);

}
