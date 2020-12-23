package aoc.y2020

import aoc.lib.Resources.rawAsList
import org.junit.Test
import kotlin.test.assertEquals

class Day142020Test {

    val input = """
        mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
        mem[8] = 11
        mem[7] = 101
        mem[8] = 0
    """.trimIndent()

    @Test
    fun ex1() {
        val solver = Day14y2020(rawAsList(input))
        assertEquals(165, solver.partOne())
    }
}
