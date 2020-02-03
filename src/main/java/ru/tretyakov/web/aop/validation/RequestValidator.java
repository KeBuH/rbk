package ru.tretyakov.web.aop.validation;

import org.springframework.stereotype.Service;

/**
 * @author Rooter
 * @since 03.02.2020
 **/

@Service
public class RequestValidator implements Validatable<String> {

    public boolean validate(final String value) {
        boolean result = true;
        if (value == null) {
            result = false;
        } else if (!value.matches("([0-9]*\\)*\\(*\\s*)+")) {
            result = false;
        }
        return result;
    }
}
