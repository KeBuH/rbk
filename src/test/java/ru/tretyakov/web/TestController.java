package ru.tretyakov.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.tretyakov.web.controllers.Controller;
import ru.tretyakov.web.dto.Request;
import ru.tretyakov.web.dto.Response;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Rooter
 * @since 03.02.2020
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {

    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan("ru.tretyakov.web")
    static class Config {

    }

    @Autowired
    private Controller controller;

    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test001_whenSuccessParseParams() throws Exception {
        mvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Request("10 20 30").toJsonString()))
                .andExpect(status().isOk())
                .andExpect(content().json(new Response("11 21 31").toJsonString()));
    }

    @Test
    public void test002_whenPassParamsWithoutSeparationByWhiteSpace() throws Exception {
        mvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Request("1").toJsonString()))
                .andExpect(status().isOk())
                .andExpect(content().json(new Response("2").toJsonString()));
    }

    @Test
    public void test003_whenPassNumbersAndCharactersThenGetError() throws Exception {
        mvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Request("1a").toJsonString()))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(new Response("unexpected request value").toJsonString()));
    }

    @Test
    public void test004_whenPassNullThenGetError() throws Exception {
        mvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Request(null).toJsonString()))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(new Response("unexpected request value").toJsonString()));
    }

    @Test
    public void test005_whenPassValueWithPlusSymbolThenGetError() throws Exception {
        mvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Request("+2").toJsonString()))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(new Response("unexpected request value").toJsonString()));
    }

    @Test
    public void test006_whenPassValueWithMinusSymbolThenGetError() throws Exception {
        mvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Request("-2").toJsonString()))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(new Response("unexpected request value").toJsonString()));
    }

    @Test
    public void test007_whenPassValueWithKCyrillicSymbolsThenGetError() throws Exception {
        mvc.perform(post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Request("ва2 2к 3").toJsonString()))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(new Response("unexpected request value").toJsonString()));
    }

}
