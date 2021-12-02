package aoc.y2021

import aoc.lib.Resources.rawAsList
import org.junit.Assert.assertEquals
import org.junit.Test

class DayTwoTest {

    val input = """
        forward 5
        down 5
        forward 8
        up 3
        down 8
        forward 2
    """.trimIndent()

    @Test
    fun test() {
        val solver = DayTwo(rawAsList(input))
        assertEquals(150, solver.partOne())
    }
}
