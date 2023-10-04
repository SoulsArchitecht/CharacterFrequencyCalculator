package ru.sshibko.CharacterFrequencyCalculator.controller;

import ru.sshibko.CharacterFrequencyCalculator.service.CharacterFrequencyService;
import ru.sshibko.CharacterFrequencyCalculator.response.ApiResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CharacterFrequencyController.class)
public class CharacterFrequencyControllerTest {

    @MockBean
    private CharacterFrequencyService service;

    @MockBean
    private ApiResult apiResult;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateFrequencyValidInput() throws Exception {
        String validInput = "abcde";

        when(service.sortResultDesc(service.calculateFrequency(validInput)))
                .thenReturn(Collections.singletonMap('a', 1));
        this.mockMvc.perform(get("/calculate-frequency")
                        .param("inputString", "abcde")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorMessage").doesNotExist())
                .andExpect(jsonPath("$.data.['a']").value("1"));
    }

    @Test
    public void testCalculateFrequencyInvalidInput() throws Exception {
        String invalidInput = "";

        when(service.calculateFrequency(invalidInput)).thenReturn(null);

        mockMvc.perform(get("/calculate-frequency")
                        .param("inputString", invalidInput)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errorMessage")
                        .value("Entered string shorter or longer than specified range"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}
