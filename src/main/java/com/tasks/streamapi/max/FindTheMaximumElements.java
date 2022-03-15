package com.tasks.streamapi.max;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FindTheMaximumElements {

    public List<BigDecimal> getThreeMaximum(List<String> input) {

        return Optional.ofNullable(input).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .filter(StringUtils::isNoneBlank)
                .map(BigDecimal::new)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
    }

}
