package ru.sshibko.CharacterFrequencyCalculator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CharacterFrequencyCalculator.response.ApiResult;
import ru.sshibko.CharacterFrequencyCalculator.service.CharacterFrequencyService;

import java.util.Map;

@RestController
public class CharacterFrequencyController {

    private final CharacterFrequencyService service;

    private ApiResult apiResult;

    private final static String ERROR_MESSAGE = "Entered string shorter or longer than specified range";

    private final static String SUCCESS_MESSAGE = "Operation completed";

    private final static Logger logger = LoggerFactory.getLogger(CharacterFrequencyController.class);

    @Autowired
    public CharacterFrequencyController(CharacterFrequencyService service, ApiResult apiResult) {
        this.service = service;
        this.apiResult = apiResult;
    }

    @GetMapping("/calculate-frequency")
    public ApiResult calculateFrequency(
            @RequestParam
            String inputString) {
        Map<Character, Integer> frequencyMap = service.calculateFrequency(inputString);
        if (frequencyMap == null) {
            logger.error(ERROR_MESSAGE);
            apiResult = new ApiResult(null, ERROR_MESSAGE);
        } else {
            logger.info(SUCCESS_MESSAGE);
            apiResult = new ApiResult(service.sortResultDesc(frequencyMap));
        }
        return apiResult;
    }
}
