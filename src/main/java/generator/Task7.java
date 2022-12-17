package generator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task7 implements Generator {

    public static List<Long> generate(int count) throws InterruptedException {

        int[] array = new int[count]; //создаем массив размерностью 13
        for (int i = 0; i < count; i++) {
            array[i] = i;
        }

        //цикл по перемешиванию элементов
        for (int i = 0; i < count; i++) {
            //генерируем два рандомных индекса массива
            int ind1 = random() % count;
            int ind2 = ind1 * random() % count;
            int temp = array[ind1];
            array[ind1] = array[ind2];
            array[ind2] = temp;
        }

        List<Long> finalArray = new ArrayList<>();
        for (int i : array) finalArray.add((long) i);
        return finalArray;
    }

    //Линейный конгруэнтный генератор:
    public static int random() {
        int randomNumber;
        int c = (int) System.currentTimeMillis();
        int a = (int) (System.currentTimeMillis() * 43);
        int x = (int) (System.currentTimeMillis() * 57);
        int m = (int) Math.pow(2, 15);
        randomNumber = (a * x + c) % m;

        return Math.abs(randomNumber);
    }

    @NotNull
    @Override
    public List<Double> generateDoubleSequence(long howManyDigitsNeedGenerate) {
        try {
            return generate((int) howManyDigitsNeedGenerate)
                    .stream()
                    .map(Double::valueOf)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public List<Long> generateLongSequence(long howManyDigitsNeedGenerate) {
        try {
            return generate((int) howManyDigitsNeedGenerate);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
