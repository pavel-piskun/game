package com.example.game.api;

import com.example.game.api.model.Figure;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void testCreateGame() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game"))
                .andExpect(status().isOk())
                .andExpect( content().string(is("1")));
    }

    @Test
    @Order(2)
    void testMakeMove() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game/1/play")
                        .content(Figure.PAPER.toString())
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect( jsonPath("humanMove", is(Figure.PAPER.toString())));
    }

    @Test
    @Order(2)
    void testMakeWrongMove() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game/1/play")
                        .content("TEST")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Order(3)
    void testGetStatistics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/game/1"))
                .andExpect(status().isOk())
                .andExpect( jsonPath("[0].humanMove", is(Figure.PAPER.toString())));
    }

    @Test
    @Order(3)
    void testCompleteGame() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game/1/complete"))
                .andExpect(status().isOk())
                .andExpect( content().string(is("ok")));
    }

    @Test
    @Order(4)
    void testMakeMoveWhenGameCompleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game/1/play")
                        .content(Figure.PAPER.toString())
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    @Order(4)
    void testMakeMoveWhenGameNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/game/2/play")
                        .content(Figure.PAPER.toString())
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
}
