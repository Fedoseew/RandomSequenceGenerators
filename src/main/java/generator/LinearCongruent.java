package generator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Линейный конгруэнтный генератор
 */
public class LinearCongruent implements Generator {

    //создаем последовательность по формуле
    public static List<Long> generate(long count) {
        ArrayList<Long> array = new ArrayList<>();
        double m = Math.pow(2, 35);
        double b = Math.pow(2, 23) + Math.pow(2, 13) + Math.pow(2, 2);
        double a = b - 1;
        double c = 7;
        double x0 = c;
        for (int i = 0; i < count; i++) {
            double x = x0;
            double res = ((a * x) + c) % m;
            x0 = res;
            array.add((long) (res));
        }
        return array;
    }

    // ищем s
    public double potential(double b, double m) {
        long s = 0;
        while (true) {
            if (Math.pow(b, s) % m == 0) break;
            else s++;
        }
        return s;
    }

    @NotNull
    @Override
    public List<Double> generateDoubleSequence(long howManyDigitsNeedGenerate) {
        return generate(howManyDigitsNeedGenerate)
                .stream()
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<Long> generateLongSequence(long howManyDigitsNeedGenerate) {
        return generate(howManyDigitsNeedGenerate);
    }
}
