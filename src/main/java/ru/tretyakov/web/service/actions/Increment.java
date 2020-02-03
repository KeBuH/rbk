package ru.tretyakov.web.service.actions;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rooter
 * @since 03.02.2020
 **/

@Component
public class Increment implements Action<String, String> {

    @Override
    public String doAction(String value) {
        return  Stream.of(value.split(" ")).map(v -> {
            int i = Integer.parseInt(v);
            return Integer.toString(++i);
        }).collect(Collectors.joining(" "));
    }

}
