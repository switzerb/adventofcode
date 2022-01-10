package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayEighteenTest {

    val solver = DayEighteen("noop")

    @Test
    fun testParse() {
        val first = "[1,2]".toList()
        val second = "[[3,4],5]".toList()
        val result = "[[1,2],[[3,4],5]]".toList()
        assertEquals(result, solver.add(first, second))
    }

    @Test
    fun testExplode1() {
        val number = "[[[[[9,8],1],2],3],4]".toList()
        val result = "[[[[0,9],2],3],4]".toList()
        assertEquals(result, solver.explode(number))
    }

    @Test
    fun testExplode2() {
        val number = "[7,[6,[5,[4,[3,2]]]]]".toList()
        val result = "[7,[6,[5,[7,0]]]]".toList()
        assertEquals(result, solver.explode(number))
    }

    @Test
    fun testExplode3() {
        val number = "[[6,[5,[4,[3,2]]]],1]".toList()
        val result = "[[6,[5,[7,0]]],3]".toList()
        assertEquals(result, solver.explode(number))
    }

    @Test
    fun testExplode4() {
        val number = "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]".toList()
        val result = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".toList()
        assertEquals(result, solver.explode(number))
    }

    @Test
    fun testExplode5() {
        val number = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".toList()
        val result = "[[3,[2,[8,0]]],[9,[5,[7,0]]]]".toList()
        assertEquals(result, solver.explode(number))
    }

    @Test
    fun testSplit1() {
        val number = "[[[[0,7],4],[15,[0,13]]],[1,1]]".toList()
        val result = "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]".toList()
        assertEquals(result, solver.split(number))
    }

    @Test
    fun testSplit2() {
        val number = "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]".toList()
        val result = "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]".toList()
        assertEquals(result, solver.split(number))
    }
}
