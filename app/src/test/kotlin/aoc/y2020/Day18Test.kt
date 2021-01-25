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

    private val example3 = """
        2 * 3 + (4 * 5)
    """.trimIndent()

    private val example4 = """
        5 + (8 * 3 + 9 + 3 * 4 * 3)
    """.trimIndent()

    private val example5 = """
        5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))
    """.trimIndent()

    private val example6 = """
        ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2
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

    @Test
    fun testMore3() {
        val solver = Day18(rawAsList(example3))
        assertEquals(26, solver.partOne())
    }

    @Test
    fun testMore4() {
        val solver = Day18(rawAsList(example4))
        assertEquals(437, solver.partOne())
    }

    @Test
    fun testMore5() {
        val solver = Day18(rawAsList(example5))
        assertEquals(12240, solver.partOne())
    }

    @Test
    fun testMore6() {
        val solver = Day18(rawAsList(example6))
        assertEquals(13632, solver.partOne())
    }

}
