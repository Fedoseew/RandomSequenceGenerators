package Generators;

import java.util.ArrayList;
import java.util.List;

/*
Генератор псевдослучайных чисел на основе выбрасывания членов последовательности
*/
public class DropTheMembersOfSequence {

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
}
