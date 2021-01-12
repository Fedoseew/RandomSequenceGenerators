package Tests;

import Generators.Task2;

import java.util.ArrayList;
import java.util.Arrays;


public class KSCriterion {

    public static void main(String[] args) {
        ArrayList<Long> array = (ArrayList<Long>) Task2.generate(90, 45577);
        array.sort(Long::compareTo);
        ArrayList<Double> newArray = new ArrayList<>();
        double scale = Math.pow(10, 3);
        double maxP = 0;
        double maxM = 0;

        for (Long x : array)
            newArray.add(Double.parseDouble(String.valueOf(Math.ceil(((double) x / 100000) * scale) / scale)));

        System.out.println(Arrays.toString(newArray.toArray()));

        int size = newArray.size();

        for (int i = 0; i < size; i++) {
            if ((double) i / size - newArray.get(i) > maxP)
                maxP = (double) i / size - newArray.get(i);

            if ((newArray.get(i) - ((double) ((i - 1) / size))) > maxM)
                maxM = newArray.get(i) - ((double) ((i - 1) / size));

        }
        double KnP = Math.ceil((Math.sqrt(size) * maxP) * scale) / scale;
        double KnM = Math.ceil((Math.sqrt(size) * maxM) * scale) / scale;
        System.out.println("Kn+ = " + KnP + ", Kn- = " + KnM);
    }


}
