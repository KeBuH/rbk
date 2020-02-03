package ru.tretyakov.web.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.tretyakov.web.dto.Response;
import ru.tretyakov.web.service.actions.Action;



/**
 * @author Rooter
 * @since 03.02.2020
 **/

@Service
public class RequestServiceImpl implements RequestService<String, ResponseEntity<Response>> {

    private final Action<String, String> increment;

    public RequestServiceImpl(final Action<String, String> increment) {
        this.increment = increment;
    }

    public ResponseEntity<Response> handle(final String request) {
        String result = this.increment.doAction(request);
        return ResponseEntity.ok(this.buildResponse(result));
    }

    private Response buildResponse(final String value) {
        return new Response(value);
    }
}
