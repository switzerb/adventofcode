package aoc.lib

import org.junit.Test
import kotlin.test.assertEquals

class VectorTest {

    @Test
    fun plus() {
        val a = Vector(1,1,1)
        val b = Vector(1,1,1)
        assertEquals(Vector(2,2,2), a + b)
    }

    @Test
    fun stringify() {
        assertEquals("(1, 2, 3)", Vector(1,2,3).toString())
    }
}
