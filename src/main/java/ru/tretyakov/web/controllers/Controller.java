package ru.tretyakov.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tretyakov.web.dto.Request;
import ru.tretyakov.web.service.RequestServiceImpl;


/**
 * @author Rooter
 * @since 03.02.2020
 **/

@RestController
public class Controller extends ControllerAdvice {

    private final RequestServiceImpl service;

    public Controller(final RequestServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE,
                                       produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object calculate(@RequestBody final Request request) {
        return this.service.handle(request.getRequest());
    }
}
