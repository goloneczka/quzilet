package pl.quiz.domain.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.quiz.domain.dto.CreatorUser;
import pl.quiz.domain.dto.vo.CreatorUserAuthVO;
import pl.quiz.domain.port.CreatorUserPersistencePort;

import java.util.List;

import static pl.quiz.domain.Authority.TEMPORARY_USER;

@RequiredArgsConstructor
public class CreatorUserAuthService implements UserDetailsService {

    private final CreatorUserPersistencePort creatorUserPersistencePort;

    private CreatorUserAuthVO getUser(String uuid){
        return creatorUserPersistencePort.getForAuth(uuid);
    }

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        CreatorUserAuthVO user = getUser(uuid);
        return new User(
                user.getName(), user.getPassword(), List.of(TEMPORARY_USER.toSimpleGrantedAuthority())
        );
    }
}
