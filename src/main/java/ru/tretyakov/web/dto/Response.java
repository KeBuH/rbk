package ru.tretyakov.web.dto;

import lombok.*;

/**
 * @author Rooter
 * @since 03.02.2020
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Response implements JSONable {

    private String response;

}
