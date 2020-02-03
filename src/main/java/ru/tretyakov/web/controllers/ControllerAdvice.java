package ru.tretyakov.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tretyakov.web.dto.Response;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;


/**
 * @author Rooter
 * @since 03.02.2020
 **/

public abstract class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exceptionHandler(final Exception ex) {
        return ResponseEntity.status(SC_INTERNAL_SERVER_ERROR).body(new Response(ex.getMessage()));
    }

}
