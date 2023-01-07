package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyThreeTest {

    val input = """
        .....
        ..##.
        ..#..
        .....
        ..##.
        .....
    """.trimIndent()

    @Test
    fun t1_p1() {
        val solver = DayTwentyThree(input)
        val expected = """
            ##
            ..
            #.
            .#
            #.
        """.trimIndent()
        assertEquals(expected, solver.field.toString())
    }

    @Test
    fun t2_p1() {
        val input = """
            ..............
            ..............
            .......#......
            .....###.#....
            ...#...#.#....
            ....#...##....
            ...#.###......
            ...##.#.##....
            ....#..#......
            ..............
            ..............
            ..............
        """.trimIndent()
        val solver = DayTwentyThree(input)
        assertEquals(110, solver.partOne())
    }

    @Test
    fun t1_p2() {
        val input = """
            ..............
            ..............
            .......#......
            .....###.#....
            ...#...#.#....
            ....#...##....
            ...#.###......
            ...##.#.##....
            ....#..#......
            ..............
            ..............
            ..............
        """.trimIndent()
        val solver = DayTwentyThree(input)
        assertEquals(20, solver.partTwo())
    }
}
