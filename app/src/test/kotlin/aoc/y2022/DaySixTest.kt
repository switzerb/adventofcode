package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DaySixTest {

    @Test
    fun test_p1e1() {
        val solver = DaySix("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
        assertEquals(7, solver.partOne())
    }

    @Test
    fun test_p1e2() {
        val solver = DaySix("bvwbjplbgvbhsrlpgdmjqwftvncz")
        assertEquals(5, solver.partOne())
    }

    @Test
    fun test_p1e3() {
        val solver = DaySix("nppdvjthqldpwncqszvftbrmjlhg")
        assertEquals(6, solver.partOne())
    }

    @Test
    fun test_p1e4() {
        val solver = DaySix("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
        assertEquals(10, solver.partOne())
    }

    @Test
    fun test_p1e5() {
        val solver = DaySix("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
        assertEquals(11, solver.partOne())
    }
}
