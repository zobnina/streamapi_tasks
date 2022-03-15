package com.tasks.streamapi.evenoddfind;

import java.util.*;
import java.util.stream.Collectors;

public class EvenAndOddFinder {

    public Map<CharacterType, Set<String>> findFromCollection(List<Set<String>> input) {

        return Arrays.stream(CharacterType.values())
                .map(characterType -> Map.entry(characterType,
                        Optional.ofNullable(input).orElse(Collections.emptyList()).stream()
                                .filter(Objects::nonNull)
                                .flatMap(Collection::stream)
                                .filter(Objects::nonNull)
                                .filter(str -> !str.isEmpty())
                                .filter(str -> CharacterType.EVEN.equals(characterType) == (str.length() % 2 == 0))
                                .collect(Collectors.toSet())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
