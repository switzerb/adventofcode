import aoc.lib.Resources.rawAsInt
import aoc.y2020.DayTen2020
import org.junit.Test
import kotlin.test.assertEquals

class DayTen2020Test {

    val input = """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent()

    val input2 = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent()

    @Test
    fun ex1() {
        val solver = DayTen2020(rawAsInt(input))
        assertEquals(7*5,solver.partOne())
    }

    @Test
    fun ex2() {
        val solver = DayTen2020(rawAsInt(input2))
        assertEquals(22*10, solver.partOne())
    }
}
