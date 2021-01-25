package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class Day18Test {

    private val example1 = """
        1 + 2 * 3 + 4 * 5 + 6
    """.trimIndent()

    private val example2 = """
        1 + (2 * 3) + (4 * (5 + 6))
    """.trimIndent()

    @Test
    fun testOrder() {
        val solver = Day18(rawAsList(example1))
        assertEquals(71, solver.partOne())
    }

    @Test
    fun testWithParens() {
        val solver = Day18(rawAsList(example2))
        assertEquals(51, solver.partOne())
    }
}
