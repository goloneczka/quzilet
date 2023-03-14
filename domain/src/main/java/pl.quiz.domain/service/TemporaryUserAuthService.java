package pl.quiz.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.domain.dto.TemporaryUserDto;
import pl.quiz.domain.port.TemporaryUserPersistencePort;

import java.util.List;

import static pl.quiz.domain.Authority.TEMPORARY_USER;

@RequiredArgsConstructor
public class TemporaryUserAuthService implements UserDetailsService {

    private final TemporaryUserPersistencePort temporaryUserPersistencePort;

    private static final String EMPTY_PASSWORD = "NO_PASSWORD_NEEDED";

    private TemporaryUserDto getTemporaryUserByUuid(String uuid){
        return temporaryUserPersistencePort.get(uuid);
    }


    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return new User(
                getTemporaryUserByUuid(uuid).getUuid(), EMPTY_PASSWORD, List.of(TEMPORARY_USER.toSimpleGrantedAuthority())
        );
    }
}
