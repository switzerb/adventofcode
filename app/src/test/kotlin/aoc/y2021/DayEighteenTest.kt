package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayEighteenTest {

    @Test
    fun testParse() {
        val input = "[1,2]"
        val solver = DayEighteen(input)
        val result = SFNum(
            left = SFNum(value = 1),
            right = SFNum(value = 2)
        )
        assertEquals(result, solver.parse(input))
    }

    @Test
    fun testParseWithNesting() {
        val input = "[[[9,8],1],2]"
        val result = SFNum(
            left = SFNum(
                left = SFNum(
                    left = SFNum(value = 9),
                    right = SFNum(value = 8)
                ),
                right = SFNum(value = 1)
            ),
            right = SFNum(value = 2)
        )
        val solver = DayEighteen(input)
        assertEquals(result, solver.parse(input))
    }

    @Test
    fun testExplode() {
//        [[9,8],1]
        val explode = SFNum(
            left = SFNum(
                left = SFNum(
                    left = SFNum(value = 9),
                    right = SFNum(value = 8)
                ),
                right = SFNum(value = 1)
            ),
            right = SFNum(value = 2)
        )

//        assertEquals(.explode("[[[[[9,8],1],2],3],4]"), "[[[[0,9],2],3],4]")
    }
}
