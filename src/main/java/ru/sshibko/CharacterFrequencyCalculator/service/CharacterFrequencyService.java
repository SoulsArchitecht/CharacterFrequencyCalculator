package ru.sshibko.CharacterFrequencyCalculator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CharacterFrequencyService {

    @Value("${stringLength.minValue}")
    private int MIN_STRING_LENGTH;

    @Value("${stringLength.maxValue}")
    private int MAX_STRING_LENGTH;

    public Map<Character, Integer> sortResultDesc(Map<Character, Integer> dataForSort) {

        return dataForSort.entrySet()
                .stream()
                .sorted((entry1, entry2) ->
                        Comparator.comparingInt((Map.Entry<Character, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                                .compare(entry1, entry2))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Map<Character, Integer> calculateFrequency(String inputString) {

        if (inputString == null || inputString.length() >= MAX_STRING_LENGTH
                || inputString.length() < MIN_STRING_LENGTH) {
            return null;
        } else {
            Map<Character, Integer> frequencyMap = new LinkedHashMap<>();
            for (char c : inputString.toCharArray()) {
                frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
            }
            return frequencyMap;
        }
    }
}
