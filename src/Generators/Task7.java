package Generators;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Task7 {
    public static List<Long> generate(int count) throws InterruptedException {

        int[] array = new int[count]; //создаем массив размерностью 13

        //заполняем массив рандомными числами и выводим его на консоль
        //System.out.println("\n\nИсходный массив чисел: ");
        for (int i = 0; i < count; i++) {

            array[i] = i;

            /*if(i==z-1)
                System.out.print(array[i]+".");

            else
                System.out.print(array[i] + ", ");
                */
        }
        //System.out.println("\n\nПолучившийся рандомный массив чисел: ");

        //цикл по перемешиванию элементов
        for (int i = 0; i < count; i++) {
            //генерируем два рандомных индекса массива
            int ind1 = random() % count;
            int ind2 = ind1 * random() % count;
            int temp = array[ind1];
            array[ind1] = array[ind2];
            array[ind2] = temp;
        }
        //выводим получившийся перемешанный массив на консоль для сравнения
        /*for(int i=0; i<z; i++){
            if(i==z-1)
                System.out.print(array[i]+".\n\n");
            else
                System.out.print(array[i]+", ");
        }*/
        List<Long> finalArray = new ArrayList<>();
        for(int i: array) finalArray.add((long)i);
        return finalArray;
    }

    //Линейный конгруэнтный генератор:
    public static int random() throws InterruptedException {
        int randomNumber = 0;
        Calendar clnd = Calendar.getInstance();
        clnd.setTime(new Date());
        int c = clnd.get(Calendar.MILLISECOND);
        Thread.sleep(3);
        int a = clnd.get(Calendar.MILLISECOND);
        Thread.sleep(2);
        int x = clnd.get(Calendar.MILLISECOND);
        int m = (int) Math.pow(2, 15);
        randomNumber = (a * x + c) % m;
        return Math.abs(randomNumber);
    }
}
