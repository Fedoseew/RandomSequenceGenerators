package generator

import kotlin.math.pow

/**
 * Метод середин квадратов
 */
class MidpointsOfSquares : Generator {

    companion object {
        private const val GENERATED_NUMBER_MIN_LENGTH = 6L // допустимая (минимальная) длина числа
    }

    override fun generateDoubleSequence(howManyDigitsNeedGenerate: Long): List<Double> {
        return generateLongSequence(howManyDigitsNeedGenerate).map { it.toDouble() }
    }

    override fun generateLongSequence(howManyDigitsNeedGenerate: Long): List<Long> {
        val result = mutableListOf<Long>() // Результирующий массив
        var seed: Long = getSeed() // Начальное число
        var counter = 1 // Счетчик сгенерированных чисел

        while (howManyDigitsNeedGenerate > counter) {
            if (result.isEmpty()) {
                result.add(seed)  // Инициализируем массив
            } else {
                seed = seed.square() // Возводим в квадрат
                seed =
                    checkSeedLength(seed) // Проверяем минимально допустимую длину и если длина меньше, возводим в квадрат еще раз
                val fromIndex: Int = ((seed.length() - GENERATED_NUMBER_MIN_LENGTH) / 2).toInt()
                var generatedNumber = seed.toString()
                    .subSequence(fromIndex, seed.length() - 1)
                    .take(GENERATED_NUMBER_MIN_LENGTH.toInt())
                    .toString()
                    .toLong()

                while (generatedNumber.length() < GENERATED_NUMBER_MIN_LENGTH) {
                    generatedNumber = checkSeedLength(generatedNumber)
                }

                seed = generatedNumber
                result.add(generatedNumber)
            }

            counter++
        }

        return result
    }

    /**
     * Провряем длину числа и возводим в квадрат еще раз, если длина меньше, обрезваем в ином случае
     */
    private fun checkSeedLength(seed: Long, mingLength: Long = GENERATED_NUMBER_MIN_LENGTH): Long {
        var rightSeed = seed

        if (seed.length() < mingLength) {
            rightSeed = seed.square()
        } else if (seed.length() > mingLength) {
            rightSeed = rightSeed
                .toString()
                .subSequence(0, mingLength.toInt() - 1)
                .toString()
                .toLong()
        }

        return rightSeed
    }

    /**
     * Получение начального числа (seed)
     */
    private fun getSeed(seedLength: Long = GENERATED_NUMBER_MIN_LENGTH): Long {
        return System.currentTimeMillis()
            .toString()
            .subSequence(0, seedLength.toInt() - 1)
            .toString()
            .replaceFirst("0", "7") // Если первое цифра "0", заменяем ее на "7"
            .toLong()
    }

    /**
     * Возведение в квадрат
     */
    private fun Long.square() = this.toDouble().pow(2).toLong()

    /**
     * Получение количества цифр в числе
     */
    private fun Long.length() = this.toString().length
}