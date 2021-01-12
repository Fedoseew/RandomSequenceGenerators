package Generators;

import java.util.*;

public class LinearCongruent {

    public static void main(String[] arr) {
        /*LinearCongruent linearCongruent = new LinearCongruent();
        linearCongruent.generate(20);
        for (Long element : linearCongruent.array) System.out.println(element); //вывод последовательности
        System.out.print("Для a = " + linearCongruent.a + "и m = " + linearCongruent.m);
        System.out.print(" потенциал s = " + linearCongruent.potential());*/
    }

    //создаем последовательность по формуле
    public static List<Long> generate(int count) {
        ArrayList<Long> array = new ArrayList<>();
        double m = Math.pow(2,35);
        double b = Math.pow(2,23) + Math.pow(2,13) + Math.pow(2,2) ;
        double a = b-1;
        double c = 7;
        double x0 = c;
        for (int i = 0; i < count; i++) {
            double x = x0;
            double res = ((a * x) + c) % m;
            x0 = res;
            array.add((long) (res));
        }
        return array;
    }
    // ищем s
    public double potential(double b, double m) {
        long s = 0;
        while (true) {
            if (Math.pow(b, s) % m == 0) break;
            else s++;
        }
        return s;
    }

}
