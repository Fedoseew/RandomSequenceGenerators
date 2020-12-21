package Generators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Task5 {
    static long c;
    static final long p = 524287;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите начальный элемент последовательности: ");
        long x0 = sc.nextLong();
        System.out.print("Введите сколько чисел требуется получить: ");
        int count = sc.nextInt();
        func(x0, count);
        sc.close();
    }

//Основная функция по генерации чисел (Обратная линейная конгруэнтная последовательность):
    public static void func(long x0, int count) {

        long a = LKG(0);
        c = LKG(1);

        System.out.print("\nSequence of numbers: "+x0+", ");

        for (int i = 0; i < count; i++) {
            if(x0==0) {
                x0=c;
            }
            else {
                long res[] = inv(x0,p);
                x0 = (a * res[1] + c) % p;
                if (i % 30 == 0 && i != 0) System.out.println("\n");
                System.out.print(x0 + ", ");
            }

        }
        System.err.print("end of sequence.\n");
    }

    //Нахождение обратного к элементу "а" по модулю "m":
    static long[] inv(long a, long b)
    {
        long res[] = new long[3]; // d, x, y
        if (b == 0)
        {
            res[0] = a; res[1] = 1; res[2] = 0;
            return res;
        }
        res = inv(b,a % b);
        long s = res[2];
        res[2] = res[1] - (a / b) * res[2];
        res[1] = s;
        return res;
    }

    //Проверка числа на простоту:
    public static boolean isPrime (long n){

        if(n==1)
            return false;
        for(int d=2; d*d<=n; d++){
            if(n%d==0)
                return false;
        }
        return true;
    }

    //Линейный конгруэнтный генератор:
    public static long LKG(int indexOfResultArray) {
        List<Long> array = new ArrayList<>();
        Date date = new Date();
        int d1 = date.getSeconds();
        long m1 = (long) Math.pow(2,25)%p;
        long b1 = (long) ((Math.pow(2,d1) + Math.pow(2,20)))%p;
        long a1 = (b1-1)%p;
        long c1 = 171;
        long x01 = c1;
        long x;
        long res;

            for (int i = 0; i < 5; i++) {
                x = x01;
                res = ((a1 * x) + c1) % m1;
                x01 = res;
                array.add(res);
            }
    return array.get(indexOfResultArray);
    }
}
