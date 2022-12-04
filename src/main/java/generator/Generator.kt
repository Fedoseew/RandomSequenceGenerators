package generator

/**
 * Базовый интерфейс для генератора
 */
interface Generator {

    object DefaultValues {
        /**
         * Сколько чисел сгенерировать
         */
        const val HOW_MANY_NUMBERS_GENERATE: Long = 100
    }

    /**
     * Основной метод генерации псевдо-случайной послеовательности
     */
    fun generateDoubleSequence(howManyDigitsNeedGenerate: Long = DefaultValues.HOW_MANY_NUMBERS_GENERATE): List<Double>
    fun generateLongSequence(howManyDigitsNeedGenerate: Long = DefaultValues.HOW_MANY_NUMBERS_GENERATE): List<Long>
}