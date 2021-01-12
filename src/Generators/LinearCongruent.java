package Generators;

import java.util.*;

public class LinearCongruent {
    double m = Math.pow(2,35);
    //double a = 3141592621d;
    //double b = a+1;
    double b = Math.pow(2,23) + Math.pow(2,13) + Math.pow(2,2) ;
    double a = b-1;
    double c = 7;
    double x0 = c;

    ArrayList<Double> array = new ArrayList<>();

    public static void main(String[] arr) {
        LinearCongruent linearCongruent = new LinearCongruent();
        linearCongruent.generate(20);
        for (Double element : linearCongruent.array) System.out.println(element); //вывод последовательности
        System.out.print("Для a = " + linearCongruent.a + "и m = " + linearCongruent.m);
        System.out.print(" потенциал s = " + linearCongruent.potential());
    }
    //создаем последовательность по формуле
    public void generate(int count) {
        for (int i = 0; i < count; i++) {
            double x = x0;
            double res = ((a * x) + c) % m;
            x0 = res;
            array.add(res);
        }
    }
    // ищем s
    public double potential() {
        long s = 0;
        while (true) {
            if (Math.pow(b, s) % m == 0) break;
            else s++;
        }
        return s;
    }

}
