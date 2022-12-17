package starter

import generator.Generator
import tester.Tester

/**
 * Базовый интерфейс для запуска программы.
 * Передаем генератор и тестировщик.
 *
 * 1. Генератор генерирует последовательность
 * 2. Тестировщик тестирует сгенерированную последовательность и выводит результат
 */
interface IStarter {

    /**
     * Дефолтный метод старта программы.
     * Запускает тестирование, печатает результат теста.
     * @see IStarter.testSequence
     */
    fun start(generator: Generator, tester: Tester) {
        println(
            "Result for" +
                    " generator [" + generator::class.java.name + "]" +
                    " and tester [" + tester::class.java.name + "]" +
                    " is " + testSequence(tester, generator)
        )
    }

    /**
     * Дефолтный метод для тестирования сгенерированной последовательности.
     * Запускает метод test у тестировщика.
     * @see Tester.test
     */
    fun testSequence(tester: Tester, generator: Generator): Any = tester.test(generator)
}