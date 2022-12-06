package tester;

import generator.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class IntervalCriterion implements Tester {

    @NotNull
    @Override
    public Object test(@NotNull Generator generator) {
        List<Long> randomSequence = generator.generateLongSequence(Generator.DefaultValues.HOW_MANY_NUMBERS_GENERATE);
        Collections.sort(randomSequence);

        List<Long> intervals = new ArrayList<>();
        for (int i = 0; i < randomSequence.size(); i++) {
            if (i == randomSequence.size() - 1) {
                intervals.add(randomSequence.get(0) - randomSequence.get(i));
                break;
            }
            intervals.add(randomSequence.get(i + 1) - randomSequence.get(i));
        }

        Collections.sort(intervals);

        Map<Object, Long> intervalsCountMap = Arrays
                .stream(intervals.toArray())
                .collect(
                        groupingBy(
                                Function.identity(), counting()
                        )
                );

        System.out.println("Interval criterion result:\n");
        intervalsCountMap.forEach((k,v) -> System.out.println("interval: " + k + ", count: " + v));

        return intervalsCountMap;
    }
}
