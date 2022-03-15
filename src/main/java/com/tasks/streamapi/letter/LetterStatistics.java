package com.tasks.streamapi.letter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LetterStatistics {

    public LinkedHashMap<Character, Integer> topFrequentLetters(List<String> strings, int topSize) {

        return new LinkedHashMap<>(Optional.ofNullable(strings)
                .orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .flatMapToInt(String::chars)
                .filter(Character::isLetter)
                .mapToObj(code -> (char) code)
                .map(Character::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(topSize)
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
