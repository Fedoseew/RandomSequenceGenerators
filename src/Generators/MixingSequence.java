package Generators;

import java.util.ArrayList;
import java.util.List;

/*
Генератор псевдослучайных чисел на основе перемешивания членов последовательности
*/
public class MixingSequence {
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

}
