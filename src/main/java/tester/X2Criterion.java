package tester;

import generator.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class X2Criterion implements Tester {

    public static int K = 7; // Небольшой модуль k, где k < 10

    /**
     * Метод для подсчёта числа вхождений элементов из переданной последовательности (по модулю k)
     */
    public static Map<Long, Integer> countOccurrences(int k, List<Long> sequence) {
        Map<Long, Integer> countOfOccurrences = new HashMap<>(); // Словарь для хранения пар "число, количество вхождений"
        for (long element : sequence) { // Пробегаем по всем элементам последовательности
            element %= k; // Сразу запишем element = element % mod - остаток от деления на k
            if (countOfOccurrences.containsKey(element)) { // Если в нашем словаре с парами уже существует пара "element, count"
                int count = countOfOccurrences.get(element);
                countOfOccurrences.put(element, count + 1); // Итерируем число вхождений для данного element в паре
            } else
                countOfOccurrences.put(element, 1); // Если в словаре такой пары нет, создаём её с единичным вхождением
        }
        return countOfOccurrences;
    }

    /**
     * Метод для подсчёта статистики V
     */
    private static String getTestValue(Map<Long, Integer> countOfOccurrences, List<Long> sequence) {
        int n = sequence.size();
        double v = 0;
        double Ps = 1.0 / countOfOccurrences.size(); // 1/k

        for (Map.Entry<Long, Integer> element : countOfOccurrences.entrySet()) {
            // Пробегаем по парам и используем число вхождений для каждого элемента (element.getValue()) в формуле
            double nPs = n * Ps;
            double Ys = element.getValue();
            double Ys_minus_nPs = Ys - nPs;
            double square = Math.pow(Ys_minus_nPs, 2);
            double statElement = square / nPs; // Вычисляем дробь под суммой
            v += statElement; // Инкриминируем наше V
        }

        return "for nyu = " + (K - 1) + ", V = " + v;
    }

    public static String doTest(List<Long> sequence) {
        Map<Long, Integer> countOccurrences = countOccurrences(K, sequence);
        return getTestValue(countOccurrences, sequence);
    }

    @NotNull
    @Override
    public String test(@NotNull Generator generator) {
        List<Long> sequence = generator.generateLongSequence(Generator.DefaultValues.HOW_MANY_NUMBERS_GENERATE);
        return doTest(sequence);
    }
}
