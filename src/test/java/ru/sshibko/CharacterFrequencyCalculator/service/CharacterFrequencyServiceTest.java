package ru.sshibko.CharacterFrequencyCalculator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CharacterFrequencyServiceTest {

    @Autowired
    private final CharacterFrequencyService frequencyService = new CharacterFrequencyService();

    @Test
    public void testSortResultDesc() {
        Map<Character, Integer> unsortedMap = new LinkedHashMap<>();
        unsortedMap.put('a', 5);
        unsortedMap.put('b', 3);
        unsortedMap.put('c', 7);

        Map<Character, Integer> sortedMap = frequencyService.sortResultDesc(unsortedMap);

        Map<Character, Integer> expectedMap = new LinkedHashMap<>();
        expectedMap.put('c', 7);
        expectedMap.put('a', 5);
        expectedMap.put('b', 3);

        assertThat(sortedMap).isEqualTo(expectedMap);
    }

    @Test
    public void testCalculateFrequency() {
        String inputString = "abracadabra";

        Map<Character, Integer> frequencyMap = frequencyService.calculateFrequency(inputString);

        assertThat(frequencyMap)
                .hasSize(5)
                .containsExactlyInAnyOrderEntriesOf(Map.of
                        ('a', 5, 'b', 2, 'r', 2, 'c', 1, 'd', 1));
    }

    @Test
    public void testCalculateFrequencyWithNullInput() {

        Map<Character, Integer> frequencyMap = frequencyService.calculateFrequency(null);

        assertThat(frequencyMap).isNull();
    }
}
