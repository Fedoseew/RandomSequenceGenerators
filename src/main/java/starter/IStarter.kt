package starter

import generator.Generator
import tester.Tester

/**
 * Базовый интерфейс для запуска программы
 * 1. Генерируем последовательность
 * 2. Тестируем последовательность
 */
interface IStarter {

    /**
     * Дефолтный метод старта программы
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
     * Дефолтный метод для тестирования сгенерируемой последовательности
     */
    fun testSequence(tester: Tester, generator: Generator): Any = tester.test(generator)
}