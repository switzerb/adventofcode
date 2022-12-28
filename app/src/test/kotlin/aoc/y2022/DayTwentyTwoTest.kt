package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyTwoTest {

    val input = """
        ...#
        .#..
        #...
        ....
...#.......#
........#...
..#....#....
..........#.
        ...#....
        .....#..
        .#......
        ......#.

10R5L5R10L4R5L5
    """.trimIndent()

    val solver = DayTwentyTwo(input)

    @Test
    fun t1_p1() {
        assertEquals(6032, solver.partOne())
    }

    @Test
    fun t2_p1() {
        val expected = """
        ...#    
        .#..    
        #...    
        ....    
...#.......#    
........#...    
..#....#....    
..........#.    
        ...#....
        .....#..
        .#......
        ......#.
"""
        assertEquals(expected, solver.parse().toString())
    }
}
