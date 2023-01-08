package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyFourTest {

    val simple = """
        #.#####
        #.....#
        #>....#
        #.....#
        #...v.#
        #.....#
        #####.#
    """.trimIndent()

    val extended = """
        #.######
        #>>.<^<#
        #.<..<<#
        #>v.><>#
        #<^v^^>#
        ######.#
    """.trimIndent()

    @Test
    fun t1_p1() {
        val solver = DayTwentyFour(extended)
        assertEquals(18, solver.partOne())
    }

    @Test
    fun t2_p1() {
        val solver = DayTwentyFour(simple)
        val expected = """
            #.#####
            #.....#
            #>....#
            #.....#
            #...v.#
            #.....#
            #####.#
            
        """.trimIndent()
        assertEquals(expected, solver.valley.toString())
    }

    @Test
    fun t3_p1() {
        val solver = DayTwentyFour(simple)
        val one = """
            #.#####
            #.....#
            #.>...#
            #.....#
            #.....#
            #...v.#
            #####.#

        """.trimIndent()
        val two = """
            #.#####
            #...v.#
            #..>..#
            #.....#
            #.....#
            #.....#
            #####.#

        """.trimIndent()

        val three = """
            #.#####
            #.....#
            #...2.#
            #.....#
            #.....#
            #.....#
            #####.#
            
        """.trimIndent()

        val four = """
            #.#####
            #.....#
            #....>#
            #...v.#
            #.....#
            #.....#
            #####.#
            
        """.trimIndent()

        val five = """
            #.#####
            #.....#
            #>....#
            #.....#
            #...v.#
            #.....#
            #####.#
            
        """.trimIndent()

        val min1 = solver.move(current = solver.valley)
        val min2 = solver.move(current = min1)
        val min3 = solver.move(current = min2)
        val min4 = solver.move(current = min3)
        val min5 = solver.move(current = min4)
        assertEquals(one, min1.toString())
        assertEquals(two, min2.toString())
        assertEquals(three, min3.toString())
        assertEquals(four, min4.toString())
        assertEquals(five, min5.toString())
    }

    @Test
    fun t4_p1() {
        val solver = DayTwentyFour(extended)
        val init = """
            #.######
            #>>.<^<#
            #.<..<<#
            #>v.><>#
            #<^v^^>#
            ######.#
            
        """.trimIndent()

        val one = """
            #.######
            #.>3.<.#
            #<..<<.#
            #>2.22.#
            #>v..^<#
            ######.#
            
        """.trimIndent()

        val two = """
            #.######
            #.2>2..#
            #.^22^<#
            #.>2.^>#
            #.>..<.#
            ######.#
            
        """.trimIndent()

        val three = """
            #.######
            #<^<22.#
            #.2<.2.#
            #><2>..#
            #..><..#
            ######.#
            
        """.trimIndent()
        val start = solver.valley
        val min1 = solver.move(start)
        val min2 = solver.move(min1)
        val min3 = solver.move(min2)
        assertEquals(init, start.toString())
        assertEquals(one, min1.toString())
        assertEquals(two, min2.toString())
        assertEquals(three, min3.toString())
    }
}
