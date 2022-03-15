package com.tasks.streamapi.sum;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class IntegerSum {

    public int integerSum(List<String> input) {

        return Optional.ofNullable(input).orElse(Collections.singletonList("0")).stream()
                .filter(Objects::nonNull)
                .flatMap(inputItem -> Arrays.stream(inputItem.split(StringUtils.SPACE)))
                .map(inputItem -> inputItem.replaceAll("[^-\\d]",StringUtils.EMPTY))
                .filter(StringUtils::isNoneBlank)
                .mapToInt(Integer::parseInt)
                .sum();
    }

}
