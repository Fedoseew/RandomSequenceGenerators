/*
Author: Kozikov Dmitriy
*/

package tester;

import generator.ConstantRecursiveSequence;
import generator.DropTheMembersOfSequence;
import generator.Generator;
import generator.MixingSequence;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class RandomPermutationCriterion {

    public static List<Long> sequence = DropTheMembersOfSequence.generateSequence(
            MixingSequence.mixSequence(
                    ConstantRecursiveSequence
                            .generateSequence(Generator.DefaultValues.HOW_MANY_NUMBERS_GENERATE)
                            .subList(0, 30000)
            )
    );
    public static int blocksSize = 3;

    public static void main(String[] args) {
        RandomPermutationCriterion randomPermutationCriterion = new RandomPermutationCriterion();
        randomPermutationCriterion.startTest(sequence, blocksSize);
    }

    public void startTest(List<Long> sequence, int blocksSize) {
        RandomPermutationCriterion randomPermutationCriterion = new RandomPermutationCriterion();
        System.out.println();
        System.out.println("Первоначальная последовательность из " + sequence.size() + " элементов:\n" + sequence + "\n");
        List<List<Long>> blocks = randomPermutationCriterion.generateBlocks(sequence, blocksSize);
        blocks = deleteBlocksWithRepeat(blocks);
        System.out.println("После разделения на блоки по " + blocksSize + " (всего " + blocks.size() + " блоков):\n" + blocks + "\n");
        List<List<Long>> standardBlocks = randomPermutationCriterion.getStandardPermutationsFromBlocks(blocks);
        System.out.println("После преобразования каждого блока к стандартной перестановке:\n" + standardBlocks + "\n");
        System.out.println("Значения f для уникальных перестановок: " + getUniqPermutations(standardBlocks) + "\n");
        HashMap<Long, Integer> mapWithFValuesAndCounts = randomPermutationCriterion.getMapWithFValuesAndCounts(standardBlocks, blocksSize);
        System.out.println("После подсчёта количества всех значений f из последовательности: " + mapWithFValuesAndCounts + "\n");
        ArrayList<Long> fValues = new ArrayList<>();
        standardBlocks.forEach(e -> fValues.add((long) calculateF(e)));
        System.out.println("Применяем хи квадрат критерий: " + X2Criterion.doTest(fValues));
    }

    // sequenceBeforeEditing - последовательность, которая будет разбиваться на блоки. blocksSize - длина блока
    public List<List<Long>> generateBlocks(List<Long> sequenceBeforeEditing, int blocksSize) {
        return ListUtils.partition(sequenceBeforeEditing, blocksSize);
    }

    // Преобразование входного блока к стандартному порядковому виду, пример: {555, 777, 666} ~= {1, 3, 2}
    public List<Long> getPermutationType(List<Long> block) {
        List<Long> sortedBlock = new ArrayList<>(block);
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

    // Подсчёт всех значений f от перестановки и их запись в Map
    public HashMap<Long, Integer> getMapWithFValuesAndCounts(List<List<Long>> standardPermutations, int blocksSize) {
        HashMap<Long, Integer> resultMap = new HashMap<>();
        standardPermutations.forEach(permutation -> {
            if (permutation.size() == blocksSize) {
                long f = calculateF(permutation);
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
        List<Long> clone = new ArrayList<>(block);
        int f = 0;
        for (int r = clone.size(); r > 1; r--) {
            long max = getMaximumFromBlock(clone, r);
            int s = clone.indexOf(max) + 1;
            f = f * r + s - 1;
            clone = swapTwoElements(clone, s, r);
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
    public List<Long> swapTwoElements(List<Long> list, int i, int k) {
        Long temp = list.get(i - 1);
        list.set(i - 1, list.get(k - 1));
        list.set(k - 1, temp);
        return list;
    }

    public HashMap<List<Long>, Integer> getUniqPermutations(List<List<Long>> standardBlocks) {
        HashMap<List<Long>, Integer> res = new HashMap<>();
        HashSet<List<Long>> blocks = new HashSet<>(standardBlocks);
        blocks.forEach(block -> res.put(block, calculateF(block)));
        return res;
    }

    public List<List<Long>> deleteBlocksWithRepeat(List<List<Long>> blocks) {
        List<List<Long>> res = new ArrayList<>();
        blocks.forEach(block -> {
            HashSet<Long> temp = new HashSet<>(block);
            if (temp.size() == block.size()) {
                res.add(block);
            }
        });
        return res;
    }
}
