package aoc.y2021

import aoc.lib.Resources.rawAsList
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertEquals

class DayFourteenTest {

    val input = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent()

    @Test
    fun test() {
        val solver = DayFourteen(input)
        assertEquals(1588, solver.partOne())
    }
}
