package ru.tretyakov.web.service.actions;

/**
 * @author Rooter
 * @since 03.02.2020
 **/

public interface Action<I, R> {

    R doAction(I value);

}
