package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DaySeventeenTest {

    val input = "x=20..30, y=-10..-5"

    @Test
    fun test() {
        /**
         * This target area means that you need to find
         * initial x,y velocity values such that after any step,
         * the probe's x position is at least 20 and at most 30,
         * and the probe's y position is at least -10 and at most -5.
         */
        val solver = DaySeventeen(input)
        assertEquals(45, solver.partOne())
    }
}
