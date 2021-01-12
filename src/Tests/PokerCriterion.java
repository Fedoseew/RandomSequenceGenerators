package Tests;

import Generators.Task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PokerCriterion {
    public static void main(String[] args) {

        List<Long> array = Task2.generate(1300, 4577);
        List<Long> block = new ArrayList<>();
        List<List<Long>> blocks = new ArrayList<>();
        int[] counter = {0,0,0,0,0};
        int d = 5;

        int tmpCounter = 1;
        for (Long x : array) {
            block.add(x%d);
            if (tmpCounter % d == 0) {
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
        System.out.println("\nИсходный массив: " + Arrays.toString(array.toArray())+"\n");
        System.out.println("Преобразованный массив блоков длины 5: " + Arrays.toString(blocks.toArray())+"\n");
        System.out.println("Подсчёт количества различных элементов: " + Arrays.toString(counter)+"\n");

        double P1 = factorial(d)/Math.pow(d, 5);
    }
    public static int factorial(int d) {
        for(int i=1; i<d; i++)
            d*=i;
        return d;
    }
}
