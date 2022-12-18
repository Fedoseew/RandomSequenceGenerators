package tester;

import generator.Generator;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class KSCriterion implements Tester {

    public static String doTest(List<Long> sequence) {
        Collections.sort(sequence); // упорядочиваем последовательность

        long m = sequence.get(sequence.size() - 1) + 1;
        int size = sequence.size();
        double n = Math.min(size, 15); // проверяем не более 15 чисел

        double maxP = Double.MIN_VALUE;
        double maxM = Double.MIN_VALUE;

        StringBuilder sequenceBuilder = new StringBuilder();

        for (int j = 0; j < n; j++) {
            double currentElement = (double) sequence.get(j) / m; // приводим число к интервалу (0, 1)

            sequenceBuilder.append(currentElement).append(", "); // для вывода тестируемой последовательности

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

        System.out.println("Changed sequence: " + sequenceBuilder);
        return "for n = " + n + " (Kn+: " + KnP + ", Kn-: " + KnM + ")";
    }

    @NotNull
    @Override
    public String test(@NotNull Generator generator) {
        return doTest(
                generator.generateLongSequence(Generator.DefaultValues.HOW_MANY_NUMBERS_GENERATE)
        );
    }
}
