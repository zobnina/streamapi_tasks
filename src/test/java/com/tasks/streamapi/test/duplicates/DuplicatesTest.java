package com.tasks.streamapi.test.duplicates;

import com.tasks.streamapi.duplicates.Duplicates;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class DuplicatesTest {

    @Test
    public void shouldReturnListWithRepeatedTwoValues() {
        Duplicates duplicates = new Duplicates();

        List<Integer> result = duplicates.findDuplicates(asList(-1, 1, 3, 2, 5, 6, -1, 3, 6), 2);

        assertEquals(asList(-1, 3, 6), result);
    }

    @Test
    public void nullValuesShouldBeOmitted() {
        Duplicates duplicates = new Duplicates();

        List<Integer> result = duplicates.findDuplicates(asList(1, 1, null, 2, 5, 6, 1, 3, 6, null), 2);

        assertEquals(singletonList(6), result);
    }

    @Test
    public void shouldReturnListWithRepeatedFourTimes() {
        Duplicates duplicates = new Duplicates();

        List<Integer> result = duplicates.findDuplicates(asList(-1, 1, 3, 2, 5, 6, 3, -1, 3, 6, 3), 4);

        assertEquals(Collections.singletonList(3), result);
    }

    @Test
    public void shouldReturnListWithRepeatedValuesThreeTimes() {
        Duplicates duplicates = new Duplicates();

        List<Integer> result = duplicates.findDuplicates(asList(-1, 6, 1, 3, 2, -1, 5, 6, -1, 3, 6), 3);

        assertEquals(asList(-1, 6), result);
    }

    @Test
    public void shouldReturnEmptyList() {
        Duplicates duplicates = new Duplicates();

        List<Integer> result = duplicates.findDuplicates(null, 2);

        assertEquals(Collections.emptyList(), result);
    }
}
