package aoc.y2020

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DayTwoTest {

    val input = """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
    """.trimIndent()

    @Test fun testPasswordValid() {
        val password = Password(1,3,'a', "abcde")
        assertTrue(password.isValid())
    }

    @Test fun testPasswordInvalid() {
        val password = Password(1,3, 'b', "cdefg")
        assertFalse(password.isValid())
    }

    @Test fun testExample1() {
        val solver = DayTwo2020(input)
        assertEquals(2, solver.partOne())
    }
}
