package com.tasks.streamapi.test.random;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomGenerator {

    public static IntStream pseudoRandomStream(int seed) {

        return Stream.iterate(seed, n -> n * n / 10 % 1000).flatMapToInt(IntStream::of);
    }
}
