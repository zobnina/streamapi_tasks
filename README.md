<h1>1. Letter:</h1>

Returns the first topSize number entries (letter -> count) of the most frequent letters in the string list the key is the capitalized letter, the value is the number of its occurrences in all strings, non-letter characters are ignored.

For example:
* Given list: "111111", null, "Pen 1", "Pineapple", "Apple", "Pen 2", "222222"
* topSize: 3
* Returned LinkedHashMap: P -> 7, E -> 5, N -> 3

<h1>2. Order:</h1>

The method returns list of the customers whose monthly total order sum is the largest 
* only the given year should be considered 
* the list should include only the given number of customers

<h1>3. Duplicates</h1>

Implement the findDuplicates method, which should return a list of values ​​that repeat numberOfDuplicates times.
For instance:

<code>findDuplicates(asList(-1,1,3,2,2,2,5,6,-1,3,6),2);</code>

should return "-1, 3 and 6" because these values ​​are repeated "numberOfDuplicates = 2" times, but not "2" which is repeated 3 times.