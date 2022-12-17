package tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaximumTCriterion {
    static int t = 8;
    static List<Double> array = new ArrayList<>();
    static List<List<Double>> blocks = new ArrayList<>();
    static List<Double> maximumsInBlocks = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            double x = Double.parseDouble(String.valueOf(Math.random()).substring(0, 4));
            if(x<1) {
                array.add((x));
            } else {
                i--;
            }
        }

        List<Double> tmp = new ArrayList<>();
        int value = t;
        for (int i = 0; i <= value; i++) {
            if (!(value + t > array.size())) {
                if (i == value - 1) {
                    tmp.add(array.get(i));
                    blocks.add(new ArrayList<>(tmp));
                    tmp.clear();
                    value += t;
                } else {
                    tmp.add(array.get(i));
                }
            }
        }

        for (List<Double> list : blocks) {
            Double max = list.stream()
                    .max(Comparator.naturalOrder())
                    .get();
            maximumsInBlocks.add(max);
        }
        maximumsInBlocks.sort(Double::compareTo);
        System.out.println("\nМассив блоков длины " + t + ": " + Arrays.toString(blocks.toArray()));
        System.out.println("\nМаксимальные значения в блоках: " + maximumsInBlocks);

        // KSCriterion.forAnyDoubleSequence(maximumsInBlocks);
    }
}
