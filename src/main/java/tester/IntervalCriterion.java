package tester;

import generator.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static generator.Generator.DefaultValues.HOW_MANY_NUMBERS_GENERATE;

@SuppressWarnings({"OptionalGetWithoutIsPresent" , "MismatchedQueryAndUpdateOfCollection"})
public class IntervalCriterion implements Tester {

    @NotNull
    @Override
    public Object test(@NotNull Generator generator) {
        List<Long> randomSequence = generator.generateLongSequence(HOW_MANY_NUMBERS_GENERATE);

        // преобразуем последовательность в последовательность чисел, принадлежащих интервалу (0, 1)
        // находим максимальное число и увеличиваем его на 1 (m)
        // далее делим все числа на m
        long m = Collections.max(randomSequence) + 1;
        List<Double> changedSequence = randomSequence
                .stream()
                .map(it -> (double) it / m)
                .toList();

        double alpha = 0; // начальное значение интервала
        double betta = 1d / 2d; // конечное значение интервала
        int t = 3; // максимальная длина подходящего интервала

        // пары вида: <индекс_в_последовательности, само_число>, где число попало в заданный интервал [alpha, betta]
        Map<Integer, Double> suitableElements = new TreeMap<>();
        for (int i = 0; i < changedSequence.size(); i++) {
            Double currentElement = changedSequence.get(i);
            if (currentElement <= betta && currentElement >= alpha) {
                suitableElements.put(i, currentElement);
            }
        }

        List<Integer> count_r = new ArrayList<>();
        List<Integer> count_t = new ArrayList<>();

        final int[] prevIndex = new int[]{ // инициализируем как самый первый индекс
                suitableElements.keySet()
                        .stream()
                        .findFirst()
                        .get()
        };

        suitableElements.forEach((index, number) -> {
            if (index != prevIndex[0]) {
                int interval = (index - prevIndex[0]) - 1; // разница индекса и предыдущего индекса
                if (interval <= t) { // если интервал не больше, чем заданное t
                    count_r.add(interval);
                } else {
                    count_t.add(interval);
                }
                prevIndex[0] = index;
            }
        });

        return applyX2Criterion(count_r, alpha, betta);
    }

    @SuppressWarnings("DuplicatedCode")
    private double applyX2Criterion(List<Integer> count_r, double alpha, double betta) {
        // Пары вида <интервал, сколько_раз_встретился>
        Map<Integer, Integer> countOfOccurrences = new TreeMap<>();
        count_r.forEach(it -> {
            Integer currentValue = countOfOccurrences.get(it);
            if (currentValue == null) {
                countOfOccurrences.put(it, 1);
            } else {
                countOfOccurrences.put(it, ++currentValue);
            }
        });

        double v = 0;
        double p = betta - alpha;
        double amountOfIntervals = calculateAmountOfIntervals(countOfOccurrences, p);

        for (Map.Entry<Integer, Integer> element : countOfOccurrences.entrySet()) {
            // (1 - p)^r * p
            double Pr = Math.pow((1 - p), element.getKey()) * p;
            double amountOfIntervals_Pr = amountOfIntervals * Pr; // ожидаемое количество раз встречи интервала
            double Yr = element.getValue(); // сколько раз встретился интервал на самом деле
            double Yr_minus_nPr = Yr - amountOfIntervals_Pr;
            double square = Math.pow(Yr_minus_nPr, 2);
            double statElement = square / amountOfIntervals_Pr;
            v += statElement;
        }

        return v;
    }

    private int calculateAmountOfIntervals(Map<Integer, Integer> countOfOccurrences, double p) {
        List<Double> Pr_Array = new ArrayList<>();
        for (Map.Entry<Integer, Integer> element : countOfOccurrences.entrySet()) {
            Pr_Array.add(
                    // (1 - p)^r * p
                    Math.pow((1 - p), element.getKey()) * p
            );
        }

        Double minPr = Collections.min(Pr_Array);
        return (int) Math.ceil(5 / minPr) * 2;
    }
}
