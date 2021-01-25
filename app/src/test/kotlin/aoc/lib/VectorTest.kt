package aoc.lib

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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

    @Test
    fun testGetNeighbors() {
        val cube = Vector(1,2,3)
        assertTrue(cube.neighbors().contains(Vector(2,2,2)))
        assertTrue(cube.neighbors().contains(Vector(0,2,3)))
    }

    @Test
    fun testAdjacentDeltas() {
        assertEquals(2, Vector.adjacentDeltas(1).size)
        assertEquals(8, Vector.adjacentDeltas(2).size)
        assertEquals(26, Vector.adjacentDeltas(3).size)
        assertEquals(80, Vector.adjacentDeltas(4).size)
    }
}
