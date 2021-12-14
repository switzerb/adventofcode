package aoc.y2021

import aoc.lib.Resources.rawAsList
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertEquals

class DayNineTest {

    val input = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()

    @Test
    fun test() {
        val solver = DayNine(rawAsList(input))
        assertEquals(15, solver.partOne())
    }
}
