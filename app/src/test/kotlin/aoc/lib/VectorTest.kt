package aoc.lib

import aoc.y2020.Day172020
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class VectorTest {

    val solver = Day172020("something")

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

    @Test
    fun testGetNeighbors() {
        val cube = Vector(1,2,3)
        assertTrue(cube.neighbors().contains(Vector(2,2,2)))
        assertTrue(cube.neighbors().contains(Vector(0,2,3)))
    }
}
