package ru.sshibko.CharacterFrequencyCalculator.response;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ApiResult {

    private Map<Character, Integer> data;

    private String errorMessage;

    public ApiResult() {
    }

    public ApiResult(Map<Character, Integer> data) {
        this.data = data;
    }

    public ApiResult(Map<Character, Integer> data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Map<Character, Integer> getData() {
        return data;
    }

    public void setData(Map<Character, Integer> data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
