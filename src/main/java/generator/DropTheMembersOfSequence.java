package generator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Генератор псевдослучайных чисел на основе выбрасывания членов последовательности
 */
public class DropTheMembersOfSequence implements Generator {

    /**
     * Основной генератор, на основе его сгенерированной последовательности будет создана новая последовательность.
     */
    public static Generator originalGenerator = new MidpointsOfSquares();

    public static ArrayList<Long> generateSequence(List<Long> sequence) {
        int n = sequence.size() / 4;
        int m = 0;
        ArrayList<Long> newSequence = new ArrayList<>();
        long N = sequence.size();
        for (int t = 0; t < N; ) {
            String number = "0." + LinearCongruent.generate(200).get(66);
            double U = Double.parseDouble(number);
            if (!((N - t) * U >= n - m)) {
                newSequence.add(sequence.get(t));
                m++;
            }
            t++;
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
        return generateSequence(originalGenerator.generateLongSequence(howManyDigitsNeedGenerate));
    }
}
