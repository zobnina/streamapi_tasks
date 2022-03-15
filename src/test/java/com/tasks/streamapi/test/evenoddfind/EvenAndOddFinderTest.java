package com.tasks.streamapi.test.evenoddfind;

import com.tasks.streamapi.evenoddfind.CharacterType;
import com.tasks.streamapi.evenoddfind.EvenAndOddFinder;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tasks.streamapi.evenoddfind.CharacterType.EVEN;
import static com.tasks.streamapi.evenoddfind.CharacterType.ODD;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class EvenAndOddFinderTest extends TestCase {

    private final EvenAndOddFinder finder = new EvenAndOddFinder();

    public void testFindFromCollection() {
        Set<String> set1 = Stream.of("additional", "use", "provide", "convenient")
                                 .collect(Collectors.toCollection(HashSet::new));

        Set<String> set2 = Stream.of("approach", "know", "Java", "breadth")
                                 .collect(Collectors.toCollection(HashSet::new));

        List<Set<String>> sets = Arrays.asList(set1, set2);


        Map<CharacterType, Set<String>> fromCollection = finder.findFromCollection(sets);

        Assert.assertEquals(5, fromCollection.get(EVEN).size());
        Assert.assertEquals(3, fromCollection.get(ODD).size());

        Set<String> forCheckEven = Stream.of("additional", "convenient", "know", "Java", "approach")
                                         .collect(Collectors.toCollection(HashSet::new));

        Assert.assertThat(forCheckEven, containsInAnyOrder(fromCollection.get(EVEN).toArray()));

        Set<String> forCheckOdd = Stream.of("use", "provide", "breadth")
                                        .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(forCheckOdd, fromCollection.get(ODD));
    }

    public void testFindFromCollectionWithNullElementsAndEmpty() {
        Set<String> set1 = Stream.of(null, "use", "provide", "", "convenient", null)
                                 .collect(Collectors.toCollection(HashSet::new));

        Set<String> set2 = Stream.of("approach", null, "know", "", "breadth", "")
                                 .collect(Collectors.toCollection(HashSet::new));

        List<Set<String>> sets = Arrays.asList(set1, set2);

        Map<CharacterType, Set<String>> fromCollection = finder.findFromCollection(sets);

        Assert.assertEquals(3, fromCollection.get(EVEN).size());
        Assert.assertEquals(3, fromCollection.get(ODD).size());

        Set<String> forCheckEven = Stream.of("convenient", "know", "approach")
                                         .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(forCheckEven, fromCollection.get(EVEN));

        Set<String> forCheckOdd = Stream.of("use", "provide", "breadth")
                                        .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(forCheckOdd, fromCollection.get(ODD));
    }

    public void testFindFromCollectionWithNullInputList() {
        Map<CharacterType, Set<String>> fromCollection = finder.findFromCollection(null);

        Assert.assertEquals(0, fromCollection.get(EVEN).size());
        Assert.assertEquals(0, fromCollection.get(ODD).size());

    }

    public void testFindFromCollectionWithNullInputSet() {
        Set<String> set1 = Stream.of(null, "use", "provide", "", "convenient", null)
                                 .collect(Collectors.toCollection(HashSet::new));

        List<Set<String>> sets = Arrays.asList(set1, null);

        Map<CharacterType, Set<String>> fromCollection = finder.findFromCollection(sets);

        Assert.assertEquals(1, fromCollection.get(EVEN).size());
        Assert.assertEquals(2, fromCollection.get(ODD).size());

        Set<String> forCheckEven = Stream.of("convenient")
                                         .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(forCheckEven, fromCollection.get(EVEN));

        Set<String> forCheckOdd = Stream.of("use", "provide")
                                        .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(forCheckOdd, fromCollection.get(ODD));
    }

    public void testFindFromCollectionWithDuplicates() {
        Set<String> set1 = Stream.of("additional", "Java", "provide", "convenient")
                                 .collect(Collectors.toCollection(HashSet::new));

        Set<String> set2 = Stream.of("Java", "know", "Java", "breadth")
                                 .collect(Collectors.toCollection(HashSet::new));

        List<Set<String>> sets = Arrays.asList(set1, set2);


        Map<CharacterType, Set<String>> fromCollection = finder.findFromCollection(sets);

        Assert.assertEquals(4, fromCollection.get(EVEN).size());
        Assert.assertEquals(2, fromCollection.get(ODD).size());

        Set<String> forCheckEven = Stream.of("additional", "convenient", "know", "Java")
                                         .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(forCheckEven, fromCollection.get(EVEN));

        Set<String> forCheckOdd = Stream.of("provide", "breadth")
                                        .collect(Collectors.toCollection(HashSet::new));

        Assert.assertEquals(forCheckOdd, fromCollection.get(ODD));
    }
}