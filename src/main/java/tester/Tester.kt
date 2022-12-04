package tester

import generator.Generator

/**
 * Базовый интерфейс для тестеровщика последовательности
 */
interface Tester {
    fun test(generator: Generator): Any
}