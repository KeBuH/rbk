package ru.tretyakov.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.tretyakov.web.dto.Request;
import ru.tretyakov.web.aop.validation.Validatable;

/**
 * @author Rooter
 * @since 03.02.2020
 **/

@Aspect
@Component
public class AspectValidation {

    private final Validatable<String> validator;

    public AspectValidation(final Validatable<String> validator) {
        this.validator = validator;
    }

    @Before("execution (* ru.tretyakov.web.controllers.Controller.calculate(..))")
    public void allMethods(JoinPoint joinPoint) {
        Request request = (Request) joinPoint.getArgs()[0];
        if (!this.validator.validate(request.getRequest())) {
            throw new IllegalArgumentException("unexpected request value");
        }
    }
}
