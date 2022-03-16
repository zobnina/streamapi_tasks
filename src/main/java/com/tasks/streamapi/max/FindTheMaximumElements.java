package com.tasks.streamapi.max;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<T> list = Optional.ofNullable(stream).orElse(Stream.empty()).collect(Collectors.toList());
        T min = list.stream().min(order).orElse(null);
        T max = list.stream().max(order).orElse(null);
        minMaxConsumer.accept(min, max);
    }
}
