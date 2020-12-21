package Generators;

import java.util.*;

public class Generator {
    double m = Math.pow(2,35);
    //double a = 3141592621d;
    //double b = a+1;
    double b = Math.pow(2,23) + Math.pow(2,13) + Math.pow(2,2) ;
    double a = b-1;
    double c = 7;
    double x0 = c;

    ArrayList<Double> array = new ArrayList<>();

    public static void main(String[] arr) {
        Generator generator = new Generator();
        generator.createArray();
        for (Double element : generator.array) System.out.println(element); //вывод последовательности
        System.out.print("Для a = " + generator.a + "и m = " + generator.m);
        System.out.print(" потенциал s = " + generator.potential());
    }
    //создаем последовательность по формуле
    public void createArray() {
        Scanner sc =  new Scanner(System.in);
        System.out.println("Введите колличество элементов в последовательности ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
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
