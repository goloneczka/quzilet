package pl.quiz.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.quiz.domain.validator.ValidationResult;
import pl.quiz.domain.validator.ValidatorUtil;

import javax.validation.Validator;

@Configuration
public class DomainConfig {

    @Bean
    ValidatorUtil validatorUtil(Validator validator) {
        return new ValidatorUtil(validator, new ValidationResult());
    }
}
