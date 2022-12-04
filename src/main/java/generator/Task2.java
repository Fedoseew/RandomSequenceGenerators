package generator;

import java.util.ArrayList;
import java.util.List;


public class Task2 {
    public static List<Long> generate(int count) {

        List<String> arr = new ArrayList<>();
        List<Long> resultArray = new ArrayList<>();
        int x = (int) (Math.random() * 1000);
        StringBuilder result;

        for (int k = 0; k < count; k++) {
            arr.clear();
            long pow;
            String l;
            String tmp = Integer.toString(x);
            int length;
            result = new StringBuilder();

            if (tmp.length() < 5) x *= 17654;

            pow = x * x;
            l = Long.toString(pow);
            length = l.length() / 2;

            for (int i = length - 2; i <= length + 2; i++)
                arr.add(Character.toString(l.charAt(i)));

            for (String s : arr)
                result.append(s);
            resultArray.add(Long.parseLong(result.toString()));

            x = Integer.parseInt(result.toString());

        }
        return resultArray;
    }
}
