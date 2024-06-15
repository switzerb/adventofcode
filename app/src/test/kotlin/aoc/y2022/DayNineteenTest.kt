package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayNineteenTest {

    val input = """
        Blueprint 1:
  Each ore robot costs 4 ore.
  Each clay robot costs 2 ore.
  Each obsidian robot costs 3 ore and 14 clay.
  Each geode robot costs 2 ore and 7 obsidian.

Blueprint 2:
  Each ore robot costs 2 ore.
  Each clay robot costs 3 ore.
  Each obsidian robot costs 3 ore and 8 clay.
  Each geode robot costs 3 ore and 12 obsidian.
    """.trimIndent()

    @Test
    fun test() {
        val solver = DayNineteen(input)
        assertEquals(33, solver.partOne())
    }

    @Test
    fun t1_p1() {
        val solver = DayNineteen(input)
        assertEquals(9, solver.partOne())
    }
}
