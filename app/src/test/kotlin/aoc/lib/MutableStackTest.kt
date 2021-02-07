package aoc.lib

import org.junit.Test
import kotlin.test.assertTrue

class MutableStackTest {

    @Test
    fun testEmpty() {
        val stack = MutableStack<Int>()
        assertTrue(stack.isEmpty())
    }
}
