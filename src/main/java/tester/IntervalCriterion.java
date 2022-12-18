package tester;

import generator.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static generator.Generator.DefaultValues.HOW_MANY_NUMBERS_GENERATE;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "MismatchedQueryAndUpdateOfCollection"})
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

        System.out.println("Changed sequence: " + changedSequence);

        double alpha = 0; // начальное значение интервала
        double betta = 1d / 2d; // конечное значение интервала
        int t = 3; // максимальная длина подходящего интервала
        int n = calculateAmountOfIntervals(betta - alpha, t);

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

        // инициализируем как самый первый индекс числа, лежащего в отрезке (alpha, betta)
        final int[] prevIndex = new int[]{
                suitableElements.keySet()
                        .stream()
                        .findFirst()
                        .get()
        };

        final boolean[] firstIteration = new boolean[]{true}; // самый первый проход по циклу
        for (Map.Entry<Integer, Double> entry : suitableElements.entrySet()) {
            if (count_t.size() + count_r.size() == n) { // если набралось n интервалов, то выходим
                break;
            }

            Integer index = entry.getKey();

            // если первое число, лежащие в отрезке (alpha, betta) находится не на 0 индексе
            if (firstIteration[0] && prevIndex[0] != 0) {
                int interval = prevIndex[0];
                if (interval < t) { // если интервал меньше, чем заданное t
                    count_r.add(interval);
                } else {
                    count_t.add(interval);
                }
            } else if (index != prevIndex[0]) {
                int interval = (index - prevIndex[0]) - 1; // разница индекса и предыдущего индекса
                if (interval < t) { // если интервал меньше, чем заданное t
                    count_r.add(interval);
                } else {
                    count_t.add(interval);
                }
                prevIndex[0] = index;
            }

            firstIteration[0] = false;
        }

        return applyX2Criterion(count_r, count_t, alpha, betta, t);
    }

    @SuppressWarnings("DuplicatedCode")
    private String applyX2Criterion(List<Integer> count_r, List<Integer> count_t, double alpha, double betta, int t) {
        // Пары вида <интервал, сколько_раз_встретился>
        Map<Integer, Integer> countOfOccurrences = new TreeMap<>();
        count_r.forEach(it -> {
            Integer currentValue = countOfOccurrences.get(it);
            if (currentValue == null) {
                countOfOccurrences.put(it, 1); // если интервал встретился впервые
            } else {
                countOfOccurrences.put(it, ++currentValue); // если уже встречался, увеличиваем количество встреч на 1
            }
        });

        double v = 0;
        double p = betta - alpha;
        double amountOfIntervals = calculateAmountOfIntervals(p, t);

        for (Map.Entry<Integer, Integer> element : countOfOccurrences.entrySet()) {
            Integer interval = element.getKey();
            if (interval < t) {
                double Pr = Math.pow((1 - p), interval) * p;
                double amountOfIntervals_Pr = amountOfIntervals * Pr; // ожидаемое количество раз встречи интервала
                double Yr = element.getValue(); // сколько раз встретился интервал на самом деле
                double Yr_minus_nPr = Yr - amountOfIntervals_Pr;
                double square = Math.pow(Yr_minus_nPr, 2);
                double statElement = square / amountOfIntervals_Pr;
                v += statElement;
            } else {
                double Pt = Math.pow((1 - p), t);
                double amountOfIntervals_Pt = amountOfIntervals * Pt;
                double Yt = count_t.size();
                double Yt_minus_nPt = Yt - amountOfIntervals_Pt;
                double square = Math.pow(Yt_minus_nPt, 2);
                double statElement = square / amountOfIntervals_Pt;
                v += statElement;
                break; // вычисляем только один раз для Pt и выходим из цикла
            }
        }

        return "for nyu = " + t + ", V = " + v;
    }

    private int calculateAmountOfIntervals(double p, int t) {
        double Pt = Math.pow((1 - p), t);
        return (int) Math.ceil(5 / Pt);
    }
}
