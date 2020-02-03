package ru.tretyakov.web.dto;

import com.google.gson.Gson;

/**
 * @author Rooter
 * @since 03.02.2020
 **/

public interface JSONable {

    default String toJsonString() {
        return new Gson().toJson(this);
    }

}
