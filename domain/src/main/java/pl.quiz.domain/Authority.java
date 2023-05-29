package pl.quiz.domain;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Authority {

    TEMPORARY_USER,
    CREATOR_USER;

    public SimpleGrantedAuthority toSimpleGrantedAuthority() {
        return new SimpleGrantedAuthority(this.toString());
    }
}
