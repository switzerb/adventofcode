package aoc.y2022

import aoc.lib.Position
import org.junit.Test
import kotlin.test.assertEquals

class DaySeventeenTest {

    val input = """
        >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
    """.trimIndent()

    val solver = DaySeventeen(input)

    @Test
    fun t1_p1() {
        assertEquals(3068, solver.partOne(rounds = 2022))
    }

    @Test
    fun t2_p1() {
        val start = Position(2, -3)
        val hline = Rock(shape = Shape.HLINE)
        val diamond = Rock(shape = Shape.DIAMOND)
        assertEquals(
            hline.moveTo(start),
            Rock(
                Shape.HLINE,
                coords = mutableListOf(
                    Position(2, -3),
                    Position(3, -3),
                    Position(4, -3),
                    Position(5, -3)
                )
            )
        )
        assertEquals(
            diamond.moveTo(start),
            Rock(
                Shape.DIAMOND,
                coords = mutableListOf(
                    Position(3, -3),
                    Position(2, -4),
                    Position(3, -4),
                    Position(4, -4),
                    Position(3, -5)
                )
            )
        )
    }
}
