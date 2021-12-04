package aoc.y2021

import aoc.lib.Resources.fileAsList
import aoc.lib.Resources.rawAsList
import org.junit.Assert.assertEquals
import org.junit.Test

class DayThreeTest {

    val input = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
    """.trimIndent()

    @Test
    fun testEx1() {
        val solver = DayThree(
            input = rawAsList(input),
            width = 5
        )
        assertEquals(solver.partOne(), 198)
    }

    @Test
    fun testDay1Actual() {
        val solver = DayThree(
            input = fileAsList("day03_2021.txt")
        )
        assertEquals(solver.partOne(), 2640986)
    }
}
