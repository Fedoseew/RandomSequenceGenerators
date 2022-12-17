package starter

import generator.MidpointsOfSquares
import tester.IntervalCriterion
import tester.KSCriterion
import tester.X2Criterion

/**
 * Базовая реализация интерфейса IStarter.
 * @see IStarter
 */
class Starter : IStarter {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            logStart()

            val generator = MidpointsOfSquares()

            val starter = Starter()
            starter.start(generator, X2Criterion())
            starter.start(generator, KSCriterion())
            starter.start(generator, IntervalCriterion())
        }

        private fun logStart() = println("Running Starter [" + this::class.java.name + "]")
    }
}