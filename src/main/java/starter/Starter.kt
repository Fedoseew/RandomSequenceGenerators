package starter

import generator.MidpointsOfSquares
import tester.IntervalCriterion
import tester.KSCriterion
import tester.X2Criterion

class Starter : IStarter {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            logStart()

            val starter = Starter()
            starter.start(MidpointsOfSquares(), X2Criterion())
            starter.start(MidpointsOfSquares(), KSCriterion())
            starter.start(MidpointsOfSquares(), IntervalCriterion())
        }

        private fun logStart() = println("Running Starter [" + this::class.java.name + "]")
    }
}