package Tests;

import Generators.Task2;
import java.util.List;

public class X2Test {
    public static void main(String[] args) throws InterruptedException {
        int d = 7;
        int[] map = new int[d];
        List<Integer> array = Task2.generate(120, 4577);
        int[] newArray = new int[array.size()];
        for(int i=0; i<newArray.length; i++) newArray[i] = array.get(i)%d;
        int k=-1;
        for(int i=0; i<newArray.length; i++) {
            if(k==6) break;
            int count = 0;
            k++;
            for (int value : newArray) {
                if (value == k) {
                    count++;
                }
            }
            map[i]=count;
        }
        double nPs = (double) newArray.length*((double)1/d);
        double result = 0.0;
        for(int i=0; i<d; i++) {
            result+=Math.pow(map[i]-nPs,2)/nPs;
        }
        System.out.println("Для v = "+(d-1)+" получилось: "+result);
    }
}
