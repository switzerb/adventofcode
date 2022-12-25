package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyTest {

    @Test
    fun t1_p1() {
        val input = """
            1
            2
            -3
            3
            -2
            0
            4
        """.trimIndent()
        val solver = DayTwenty(input)
        assertEquals(3, solver.partOne())
    }

    @Test
    fun t2_p1() {
        val input = """
            4
            5 
            6 
            1
            7 
            8 
            9
        """.trimIndent()
        val solver = DayTwenty(input)
//        solver.move(3)
//        assertEquals(listOf(4, 5, 6, 7, 1, 8, 9), solver.mixed.toList())
    }

    @Test
    fun t4_p1() {
        val input = """
            4
            -2
            5
            6
            7
            8
            9
        """.trimIndent()
        val solver = DayTwenty(input)
//        solver.move(1)
//        assertEquals(listOf(4, 5, 6, 7, 8, -2, 9), solver.mixed.toList())
    }
}
