package Generators;

import java.util.ArrayList;
import java.util.List;


public class Task2 {
    public static List<Integer> generate(int count, int firstelement) {

        List<String> arr = new ArrayList<>();
        List<Integer> resultArray = new ArrayList<>();

        int x = firstelement;
        StringBuilder result = null;
        for (int k = 0; k < count; k++) {
            arr.clear();
            long pow = 0;
            String l = "";
            String tmp = Integer.toString(x);
            int length = 0;
            result = new StringBuilder();

            if (tmp.length() < 5) x *= 17654;

            pow = x * x;
            l = Long.toString(pow);
            length = l.length() / 2;

            for (int i = length - 2; i <= length + 2; i++)
                arr.add(Character.toString(l.charAt(i)));

            for (String s : arr)
                result.append(s);
            resultArray.add(Integer.parseInt(result.toString()));

            x = Integer.parseInt(result.toString());

        }
        return resultArray;
    }
}
