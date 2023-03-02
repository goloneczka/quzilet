package pl.quiz.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import pl.quiz.ControllerMapping;
import pl.quiz.domain.Authority;
import pl.quiz.domain.service.TemporaryUserAuthService;

@EnableWebSecurity(debug = false)
public class SecurityConfiguration {

    @Configuration
    @Order(1)
    @AllArgsConstructor
    public static class WebSecurityForTempAuth extends WebSecurityConfigurerAdapter {

        private final TemporaryUserAuthService temporaryUserAuthService;

        protected void configure(AuthenticationManagerBuilder auth) {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(temporaryUserAuthService);
            auth.authenticationProvider(authenticationProvider);
        }

        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .requestMatchers()
                    .antMatchers(ControllerMapping.OPEN_ROOM)
                .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(ControllerMapping.OPEN_ROOM).hasAuthority(Authority.TEMPORARY_USER.toString())
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .httpBasic().authenticationEntryPoint(prepareAuthEntryPoint());
        }

        private AuthenticationEntryPoint prepareAuthEntryPoint() {
            return ((request, response, authException) -> {
                response.sendError(HttpStatus.UNAUTHORIZED.value());
            });
        }
    }

    @Configuration
    @Order(2)
    @AllArgsConstructor
    public static class WebSecurityForDefaultAuth extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .requestMatchers()
                    .antMatchers("/**")
                    .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/**")
                    .permitAll();
        }


    }
}