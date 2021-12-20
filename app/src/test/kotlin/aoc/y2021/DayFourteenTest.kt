package aoc.y2021

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

    val solver = DayFourteen(input)

    @Test
    fun test() {
        val solver = DayFourteen(input)
        assertEquals(1588, solver.partOne())
    }

    @Test
    fun testRun() {
        val solver = DayFourteen(input)
        assertEquals(0, solver.run(10))
    }

    @Test
    fun testExpandPolymer() {
        // stepone: NCNBCHB
        val start = mapOf(
            "NN" to 0,
            "NC" to 0,
            "CB" to 0,
        )
        val result = mapOf(
            "NC" to 1,
            "CN" to 1,
            "NB" to 1,
            "BC" to 1,
            "CH" to 1,
            "HB" to 1,
        )
        assertEquals(result, solver.expandPolymer(start))
    }

    @Test
    fun testOccurrances() {
        // After one step we should have
        //
        val polymer = "NCNBCHB"
        val result = mapOf(
            'N' to 2,
            'C' to 2,
            'B' to 2,
            'H' to 1,
        )
        assertEquals(result, solver.occurances(polymer))
    }
}
