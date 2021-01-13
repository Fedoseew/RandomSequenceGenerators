package Tests;

import Generators.LinearCongruent;
import Generators.Task2;
import Generators.Task5;
import Generators.Task7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PokerCriterion {
    public static void main(String[] args) {

        List<Long> array = Task2.generate(1000, 17355);
        List<Long> block = new ArrayList<>();
        List<List<Long>> blocks = new ArrayList<>();
        int[] counter = {0,0,0,0,0};
        int d = 10;
        int R = 5;


        int tmpCounter = 1;
        for (Long x : array) {
            block.add(x%d);
            if (tmpCounter % R == 0) {
                blocks.add(List.copyOf(block));
                block.clear();
                tmpCounter = 1;
            } else {
                tmpCounter++;
            }
        }

        for (List<Long> x : blocks) {
            int count = 1;
            List<Long> tmpArr = new ArrayList<>();

            for (int k = 0; k < x.size(); k++) {
                for (int j = 0; j < x.size(); j++) {
                    if (j == 0) {
                        tmpArr.add(x.get(0));
                    } else {
                        if (!tmpArr.contains(x.get(j))) {
                            tmpArr.add(x.get(j));
                            count++;
                        }
                    }
                }
            }
            tmpArr.clear();
            switch (count) {
                case 5 -> counter[4]++;
                case 4 -> counter[3]++;
                case 3 -> counter[2]++;
                case 2 -> counter[1]++;
                case 1 -> counter[0]++;
            }
        }
        System.out.println("\nИсходный массив: " + Arrays.toString(array.toArray()));
        System.out.println("\nМассив блоков длины 5 (mod " + d + "): " + Arrays.toString(blocks.toArray()));
        System.out.println("\nПодсчёт количества различных элементов: " + Arrays.toString(counter));

        double[] Ps = new double[6];
        Ps[0]=0;

        for(int r = 1; r<6; r++) {
            int chisltel = d*(d-1);
                for (int k = 2; k < r + 2; k++) {
                    if(d-k > d-r+1) {
                        chisltel *= (d - k);
                    }
                    else {
                        chisltel *= (d - r + 1);
                        break;
                    }
                }
            Ps[r] = chisltel*StirlingNumbers.func(5,r)/Math.pow(d,5);
        }
        System.out.println("\nВероятности: " + Arrays.toString(Ps));
        List<Integer> finalArray = new ArrayList<>();
        for (int j : counter) finalArray.add(j);
        X2Criterion.forPokerCriterion(finalArray, d, Ps);
    }
}
