package generator

import java.math.BigInteger
import kotlin.math.pow

/**
 * Метод середин квадратов
 */
class MidpointsOfSquares : Generator {

    companion object {
        private val MOD = BigInteger.valueOf(
            2.toDouble().pow(25).toLong()
        )
    }

    override fun generateDoubleSequence(howManyDigitsNeedGenerate: Long): List<Double> {
        return generateLongSequence(howManyDigitsNeedGenerate).map { it.toDouble() }
    }

    override fun generateLongSequence(howManyDigitsNeedGenerate: Long): List<Long> {
        val result = mutableListOf<Long>() // Результирующий массив
        var seed: BigInteger = getSeed() // Начальное число
        var counter = 1 // Счетчик сгенерированных чисел

        while (howManyDigitsNeedGenerate > counter) {
            val generatedNumber = ((seed.multiply(seed.add(BigInteger.valueOf(1)))).mod(MOD))
            seed = generatedNumber
            result.add(generatedNumber.toLong())
            counter++
        }

        return result.also { it.printSequence() }
    }

    private fun List<Long>.printSequence() =
        println("Sequence: $this")

    /**
     * Получение начального числа (seed)
     */
    private fun getSeed() =
        findSeedWithParams(System.currentTimeMillis())

    /**
     * Подбор нужного начального числа, исходя из параметров
     * @return resultSeed = [initialSeed] сравним с [reminderOfDivision] по модулю [mod]
     */
    private fun findSeedWithParams(initialSeed: Long, mod: Long = 4, reminderOfDivision: Long = 2): BigInteger {
        var resultSeed = BigInteger.valueOf(initialSeed)
        while (!resultSeed.mod(BigInteger.valueOf(mod))
                .equals(BigInteger.valueOf(reminderOfDivision))) {
            resultSeed = resultSeed.inc()
        }
        return resultSeed
    }
}