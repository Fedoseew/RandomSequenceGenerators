/*
Author: Kozikov Dmitriy
*/
package Tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class X2Test {

    /* Метод для подсчёта числа вхождений элементов из переданной последовательности (по модулю d) */
    public static Map<Long, Integer> countOccurrences(int d, List<Long> sequence) {
        Map<Long, Integer> countOfOccurrences = new HashMap<>(); // Словарь для хранения пар "число, количество вхождений"
        for (long element : sequence) { // Пробегаем по всем элементам последовательности
            element %= d; // Сразу запишем element = element % mod - остаток от деления на d
            if (countOfOccurrences.containsKey(element)) { // Если в нашем словаре с парами уже существует пара "element, count"
                int count = countOfOccurrences.get(element);
                countOfOccurrences.put(element, count + 1); // Иттерируем число вхождений для данного element в паре
            } else
                countOfOccurrences.put(element, 1); // Если в словаре такой пары нет, создаём её с единичным вхождением
        }
        return countOfOccurrences;
    }

    /* Метод для подсчёта статистики V */
    public static double getTestValue(Map<Long, Integer> countOfOccurrences, int n) {
        double v = 0;
        double ps = 1.0 / countOfOccurrences.size();
        for (Map.Entry<Long, Integer> element : countOfOccurrences.entrySet()) {
            // Пробегаем по парам и используем число вхождений для каждого элемента (element.getValue()) в формуле
            double nps = n * ps;
            double valueFromMap = element.getValue();
            double valueFromMapMinusNps = valueFromMap - nps;
            double doubleValueFromMapMinusNps = Math.pow(valueFromMapMinusNps, 2);
            double statElement = (doubleValueFromMapMinusNps) / nps; // Вычисляем дробь под суммой
            v += statElement; // Инкрементируем наше V
        }
        return v;
    }

    public static double getValue(List<Long> sequence) {
        int d = 7; // Небольшой модуль d, где d < 10
        Map<Long, Integer> countOccurrences = countOccurrences(d, sequence);
        return getTestValue(countOccurrences, sequence.size());
    }
}
