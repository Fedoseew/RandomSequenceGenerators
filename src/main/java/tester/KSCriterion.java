package tester;

import generator.Generator;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class KSCriterion implements Tester {

    public static Pair<String, String> doTest(List<Long> sequence) {
        Collections.sort(sequence); // упорядочиваем последовательность

        long m = sequence.get(sequence.size() - 1) + 1;
        int size = sequence.size();
        double n = Math.min(size, 15); // проверяем не более 20 чисел

        double maxP = Integer.MIN_VALUE;
        double maxM = Integer.MIN_VALUE;

        for (int j = 0; j < n; j++) {
            double currentElement = (double) sequence.get(j) / m; // приводим число к интервалу (0, 1)

            double KnP_Value = (double) j / n - currentElement;
            if (KnP_Value > maxP) {
                maxP = KnP_Value;
            }

            double KnM_Value =  (double) (j - 1) / n;
            if ((currentElement - KnM_Value) > maxM) {
                maxM = currentElement - KnM_Value;
            }
        }

        double KnP = (Math.sqrt(n) * maxP);
        double KnM = (Math.sqrt(n) * maxM);

        return new Pair<>("Kn+: " + KnP, "Kn-: " + KnM);
    }

    @NotNull
    @Override
    public Pair<String, String> test(@NotNull Generator generator) {
        return doTest(
                generator.generateLongSequence(Generator.DefaultValues.HOW_MANY_NUMBERS_GENERATE)
        );
    }
}
