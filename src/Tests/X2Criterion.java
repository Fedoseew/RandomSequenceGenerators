package Tests;

import Generators.Task2;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class X2Criterion {

    public static void main(String[] args) {
        int d = 7;
        int[] map = new int[d];
        List<Long> array = Task2.generate(120);
        long[] newArray = new long[array.size()];

        for (int i = 0; i < newArray.length; i++) newArray[i] = array.get(i) % d;
        int k = -1;

        for (int i = 0; i < newArray.length; i++) {
            if (k == 6) break;
            int count = 0;
            k++;

            for (long value : newArray) {
                if (value == k)
                    count++;
            }
            map[i] = count;
        }
        System.out.println(Arrays.toString(map));
        double nPs = (double) newArray.length * ((double) 1 / d);
        double result = 0.0;
        for (int i = 0; i < d; i++) {
            result += Math.pow(map[i] - nPs, 2) / nPs;
        }
        System.out.println("Для v = " + (d - 1) + " получилось: " + result);
    }

    public static void forCollectingCouponsCriterion(List<Map.Entry<Integer, Long>> array, int d, double[] Ps) {
        double result = 0.0;
        int summ = 0;
        for(Map.Entry<Integer, Long> entry: array) {
            summ+=entry.getValue();
        }
        for (int i = 5; i < array.size(); i++) {
            result += Math.pow((array.get(i).getValue() - (Ps[array.get(i).getKey()]*summ)), 2) / (Ps[array.get(i).getKey()]*summ);
        }
        System.out.println("\nДля v = " + (d-1) + " получилось: " + result);
    }

    public static void forPokerCriterion(List<Integer> array, int d, double[] Ps){
        double result = 0.0;
        int summ = 0;
        for(int i: array) summ += i;
        for(int i=0; i<array.size(); i++) {
            result += Math.pow((array.get(i) - (Ps[i+1]*summ)), 2) / (Ps[i+1]*summ);
        }
        System.out.println("\nДля v = " + (d-1) + " получилось: " + result);
    }
}
