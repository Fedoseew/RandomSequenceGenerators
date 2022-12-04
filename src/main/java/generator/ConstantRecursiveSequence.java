package generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
Генератор псевдослучайных чисел на основе Линейных рекуррентных последовательностей
*/
public class ConstantRecursiveSequence {
    private static final int p = 997; // Большое простое число
    private static final int k = 4; // Число иксов в последовательности
    private static final ArrayList<Integer> primeRoots = generatePRoots(); // Первообразные корни
    private static final ArrayList<Integer> ordPMinusOne = findBaseOfOrd(p - 1); // Все элементы порядка p-1
    private static ArrayList<Long> x; // Первоначальные x (k штук)
    private static ArrayList<Integer> a; // Первоначальные а (k штук)

    public static ArrayList<Long> generateSequence() {
        createAllX(); // Создаём элементы x;
        createAllA(); // Создаём элементы а;
        return generateAllNumbers();
    }

    private static void createAllX() { // Метод создаёт и записывает k штук "х" из элементов порядка p-1
        x = new ArrayList<>();
        for (int i = 0; i < ConstantRecursiveSequence.k; i++) {
            x.add(Long.valueOf(primeRoots.get(i)));
        }
    }

    private static void createAllA() { // Метод создаёт и записывает k штук "а" из первообразных корней mod p
        a = new ArrayList<>();
        for (int i = 0; i < ConstantRecursiveSequence.k; i++)
            a.add(ordPMinusOne.get(ordPMinusOne.size() - 6 + i));
    }

    private static ArrayList<Integer> generatePRoots() { // Метод для поиска и записи всех первообразных корней
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            if (isPRoot(i))
                res.add(i);
        }
        return res;
    }

    private static boolean isPRoot(long a) { // Прроверка числа на принадлежность к группе первообразных кореней
        if (a == 0 || a == 1)
            return false;
        long last = 1;

        Set<Long> set = new HashSet<>();
        for (int i = 0; i < p - 1; i++) {
            last = (last * a) % p;
            if (set.contains(last))
                return false;
            set.add(last);
        }
        return true;
    }

    private static int generateRandomNumber() { // Сгенерировать следующий член последовательности
        int number = 0;
        for (int i = 0; i < k; i++) {
            number += x.get(i) * a.get(i);
            number %= p;
        }
        x.add((long) number);
        x.remove(0);
        return number;
    }

    public static ArrayList<Integer> findBaseOfOrd(int q) { // Найти все элементы конкретного порядка
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 2; i < p; i++) {
            double temp = powP(i, q);
            if (temp == 1) {
                res.add(i);
            }
        }
        return res;
    }

    protected static double powP(double base, double exponent) { // Возведение в степень по модулю (p)
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
            result %= p;
        }
        return result % p;
    }

    private static ArrayList<Long> generateAllNumbers() {
        ArrayList<Long> allNumbers = new ArrayList<>(x);
        while (true) {
            long x1 = generateRandomNumber();
            long x2 = generateRandomNumber();
            long x3 = generateRandomNumber();
            long x4 = generateRandomNumber();
            if (x1 == allNumbers.get(0) && x2 == allNumbers.get(1) && x3 == allNumbers.get(2) && x4 == allNumbers.get(3))
                break;
            else {
                allNumbers.add(x1);
                allNumbers.add(x2);
                allNumbers.add(x3);
                allNumbers.add(x4);
            }
        }
        return allNumbers;
    }
}
