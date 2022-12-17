package generator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Генератор псевдослучайных чисел на основе перемешивания членов последовательности
 */
public class MixingSequence implements Generator {

    /**
     * Основной генератор, на основе его сгенерированной последовательности будет создана новая последовательность.
     */
    public static Generator originalGenerator = new MidpointsOfSquares();

    public static ArrayList<Long> mixSequence(List<Long> sequence) {
        ArrayList<Long> newSequence = new ArrayList<>(sequence);
        for (int j = newSequence.size() - 1; j >= 0; j--) {
            String number = "0." + LinearCongruent.generate(100).get(50);
            double U = Double.parseDouble(number);
            int k = (int) (j * U) + 1;
            long tempK = newSequence.get(k);
            newSequence.set(k, newSequence.get(j));
            newSequence.set(j, tempK);
        }
        return newSequence;
    }

    @NotNull
    @Override
    public List<Double> generateDoubleSequence(long howManyDigitsNeedGenerate) {
        return generateLongSequence(howManyDigitsNeedGenerate)
                .stream()
                .map(Double::valueOf)
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    public List<Long> generateLongSequence(long howManyDigitsNeedGenerate) {
        return mixSequence(originalGenerator.generateLongSequence(howManyDigitsNeedGenerate));
    }
}
