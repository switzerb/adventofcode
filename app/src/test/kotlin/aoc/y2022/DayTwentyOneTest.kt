package aoc.y2022

import org.junit.Test
import kotlin.test.assertEquals

class DayTwentyOneTest {

    val input = """
        root: pppw + sjmn
        dbpl: 5
        cczh: sllz + lgvd
        zczc: 2
        ptdq: humn - dvpt
        dvpt: 3
        lfqf: 4
        humn: 5
        ljgn: 2
        sjmn: drzm * dbpl
        sllz: 4
        pppw: cczh / lfqf
        lgvd: ljgn * ptdq
        drzm: hmdt - zczc
        hmdt: 32
    """.trimIndent()

    @Test
    fun t1_p1() {
        val solver = DayTwentyOne(input)
        assertEquals(152, solver.partOne())
    }
}
