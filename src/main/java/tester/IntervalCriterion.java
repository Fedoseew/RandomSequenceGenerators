package tester;

import generator.Task5;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class IntervalCriterion {

    public static void main(String[] args) {
        List<Long> randomSequence = new ArrayList<>(Task5.generate(1523));
        List<Long> intervals = new ArrayList<>();
        Collections.sort(randomSequence);

        for(int i=0; i<randomSequence.size(); i++) {
            if(i==randomSequence.size()-1) {
                intervals.add(randomSequence.get(0) - randomSequence.get(i));
                break;
            }
            intervals.add(randomSequence.get(i+1) - randomSequence.get(i));
        }

        Collections.sort(intervals);

        Map<Object, Long> equalsIntervals = Arrays.stream(intervals.toArray()).collect(groupingBy(Function.identity(), counting()));

    }
}
