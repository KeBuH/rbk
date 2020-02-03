package ru.tretyakov.web.aop.validation;

/**
 * @author Rooter
 * @since 03.02.2020
 **/

public interface Validatable<T> {

    boolean validate(T value);

}
