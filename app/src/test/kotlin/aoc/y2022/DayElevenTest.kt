package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayElevenTest {

    val input = listOf(
        Monkey(
            id = 0,
            items = listOf(79, 98),
            worryOperation = { w -> w * 19 },
            divider = 23,
            monkeyTId = 2,
            monkeyFId = 3
        ),
        Monkey(
            id = 1,
            items = listOf(54, 65, 75, 74),
            worryOperation = { w -> w + 6 },
            divider = 19,
            monkeyTId = 2,
            monkeyFId = 0
        ),
        Monkey(
            id = 2,
            items = listOf(79, 60, 97),
            divider = 13,
            worryOperation = { w -> w * w },
            monkeyTId = 1,
            monkeyFId = 3
        ),
        Monkey(
            id = 3,
            items = listOf(74),
            divider = 17,
            worryOperation = { w -> w + 3 },
            monkeyTId = 0,
            monkeyFId = 1
        )
    )

    @Test
    fun test() {
        val solver = DayEleven(input)
        assertEquals(0, solver.partOne())
    }
}
