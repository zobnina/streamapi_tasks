package com.tasks.streamapi.duplicates;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Duplicates {

    public List<Integer> findDuplicates(List<Integer> integerList, int numberOfDuplicates) {

        return Optional.ofNullable(integerList).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> numberOfDuplicates == entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
