/*
Author: Kozikov Dmitriy
*/

package Tests;

import Generators.Task2;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RandomPermutationTest {

    public static List<Long> sequence = Task2.generate(100);
    public static int blocksSize = 3;

    public static void main(String[] args) {
        RandomPermutationTest randomPermutationTest = new RandomPermutationTest();
        randomPermutationTest.startTest(sequence, blocksSize);
    }

    public void startTest(List<Long> sequence, int blocksSize) {
        RandomPermutationTest randomPermutationTest = new RandomPermutationTest();
        System.out.println();
        System.out.println("Первоначальная последовательность из 100 элементов:\n" + sequence + "\n");
        List<List<Long>> blocks = randomPermutationTest.generateBlocks(sequence, blocksSize);
        System.out.println("После разделения на блоки по " + blocksSize + ":\n" + blocks + "\n");
        System.out.println("После преобразования каждого блока к стандартной перестановке:\n" +
                randomPermutationTest.getStandardPermutationsFromBlocks(blocks) + "\n");
        System.out.println("После подсчёта всех функций f и их количества: ");
        System.out.println(randomPermutationTest
                .getMapWithFValuesAndCounts(randomPermutationTest
                        .getStandardPermutationsFromBlocks(blocks), blocksSize));
    }

    // sequenceBeforeEditing - последовательность, которая будет разбиваться на блоки. blocksSize - длина блока
    public List<List<Long>> generateBlocks(List<Long> sequenceBeforeEditing, int blocksSize) {
        return ListUtils.partition(sequenceBeforeEditing, blocksSize);
    }

    // Преобразование входного блока к стандартному порядковому виду, пример: {555, 777, 666} ~= {1, 3, 2}
    public List<Long> getPermutationType(List<Long> block) {
        List<Long> sortedBlock = new ArrayList<Long>(block);
        Collections.sort(sortedBlock);
        List<Long> permutation = new ArrayList<>();
        for (long element : block) {
            permutation.add((long) (sortedBlock.indexOf(element) + 1));
        }
        return permutation;
    }

    // Преобразование всей входной последовательности из блоков к стандартному порядковому виду, см. getPermutationType()
    public List<List<Long>> getStandardPermutationsFromBlocks(List<List<Long>> blocks) {
        List<List<Long>> resultList = new ArrayList<>();
        blocks.forEach(block -> resultList.add(getPermutationType(block)));
        return resultList;
    }

    // Подсчёт всех стандартных перестановок и их запись в Map
    public HashMap<List<Long>, Integer> getMapWithPermutationsAndCounts(List<List<Long>> standardPermutations, int blocksSize) {
        HashMap<List<Long>, Integer> resultMap = new HashMap<>();
        standardPermutations.forEach(permutation -> {
            if (permutation.size() == blocksSize) {
                if (resultMap.containsKey(permutation)) {
                    resultMap.put(permutation, resultMap.get(permutation) + 1);
                } else {
                    resultMap.put(permutation, 1);
                }
            }
        });
        return resultMap;
    }

    // Подсчёт всех значений f от перестановки и их запись в Map
    public HashMap<Integer, Integer> getMapWithFValuesAndCounts(List<List<Long>> standardPermutations, int blocksSize) {
        HashMap<Integer, Integer> resultMap = new HashMap<>();
        standardPermutations.forEach(permutation -> {
            if (permutation.size() == blocksSize) {
                int f = calculateF(permutation);
                if (resultMap.containsKey(f)) {
                    resultMap.put(f, resultMap.get(f) + 1);
                } else {
                    resultMap.put(f, 1);
                }
            }
        });
        return resultMap;
    }

    // Подсчёт функции f
    public int calculateF(List<Long> block) {
        int f = 0;
        for (int r = block.size(); r > 1; r--) {
            long max = getMaximumFromBlock(block, r);
            int s = block.indexOf(max) + 1;
            f = f * r + s - 1;
            swapTwoElements(block, s, r);
        }
        return f;
    }

    // Поиск максимального элемента в переданном блоке до указанного индекса
    public long getMaximumFromBlock(List<Long> block, int beforeThisIndex) {
        long max = block.get(0);
        for (long i = 0; i < beforeThisIndex; i++) {
            if (block.get((int) i) > max) {
                max = block.get((int) i);
            }
        }
        return max;
    }

    // Перестановка двух элементов в блоке местами
    public void swapTwoElements(List<Long> list, int i, int k) {
        Long temp = list.get(i - 1);
        list.set(i - 1, list.get(k - 1));
        list.set(k - 1, temp);
    }
}
