package tester;

import generator.LinearCongruent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectingCouponsCriterion {

    public static void main(String[] args) {

        List<Long> array = LinearCongruent.generate(1000);
        System.out.println(array);
        List<Integer> countOfLengths = new ArrayList<>();

        array.replaceAll(aLong -> (Math.abs(aLong % 5)));

        System.out.println("\nИсходный массив по (mod 5): " + Arrays.toString(array.toArray()));

        int size = 0;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < array.size(); i++) {
            if (!(set.size() == 5)) {
                set.add(array.get(i));
            } else {
                countOfLengths.add(i - size);
                size = i;
                set.clear();
            }
        }

        System.out.println("\nДлины отрезков: " + Arrays.toString(countOfLengths.toArray()));

        int maxLength = 15;
        countOfLengths = countOfLengths.stream()
                .filter(x -> x < maxLength)
                .collect(Collectors.toList());

        List<Map.Entry<Integer, Long>> rs = countOfLengths
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .toList();

        System.out.println("\nСколько раз встретилась длина: " + rs);

        double[] Ps = new double[maxLength];

        for (int i = 0; i < 5; i++) {
            Ps[i] = 0;
        }

        for (int i = 5; i < maxLength; i++) {
            Ps[i] = (120 * StirlingNumbersCriterion.func(i - 1, 4)) / Math.pow(5, i);
        }

        System.out.println("\nВероятности длин: " + Arrays.toString(Ps));

        // X2Criterion.forCollectingCouponsCriterion(rs, 10, Ps);
    }
}
