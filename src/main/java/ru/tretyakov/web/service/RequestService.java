package ru.tretyakov.web.service;


/**
 * @author Rooter
 * @since 03.02.2020
 **/

public interface RequestService<I, R> {

    R handle(final I value);

}
