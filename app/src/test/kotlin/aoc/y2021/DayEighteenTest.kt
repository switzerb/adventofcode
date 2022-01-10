package aoc.y2021

import org.junit.Test
import kotlin.test.assertEquals

class DayEighteenTest {

    val noop = DayEighteen(listOf("noop"))
    private fun String.toSnailFishNumber() = split("")

    @Test
    fun testParse() {
        val first = "[1,2]".toSnailFishNumber()
        val second = "[[3,4],5]".toSnailFishNumber()
        val result = "[[1,2],[[3,4],5]]".toSnailFishNumber()
        assertEquals(result, noop.add(first, second))
    }

    @Test
    fun testExplode1() {
        val number = "[[[[[9,8],1],2],3],4]".toSnailFishNumber()
        val result = "[[[[0,9],2],3],4]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode2() {
        val number = "[7,[6,[5,[4,[3,2]]]]]".toSnailFishNumber()
        val result = "[7,[6,[5,[7,0]]]]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode3() {
        val number = "[[6,[5,[4,[3,2]]]],1]".toSnailFishNumber()
        val result = "[[6,[5,[7,0]]],3]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode4() {
        val number = "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]".toSnailFishNumber()
        val result = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testExplode5() {
        val number = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".toSnailFishNumber()
        val result = "[[3,[2,[8,0]]],[9,[5,[7,0]]]]".toSnailFishNumber()
        assertEquals(result, noop.explode(number))
    }

    @Test
    fun testSplit1() {
        val number = "[[[[0,7],4],[".split("") + listOf("15", ",", "[", "0", ",", "13") + "]]],[1,1]]".split("")
        val result = "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"
        assertEquals(result, noop.split(number).joinToString(""))
    }

    @Test
    fun testSplit2() {
        val number = "[[[[0,7],4],[[7,8],[0,".split("") + listOf("13") + "]]],[1,1]]".split("")
        val result = "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]"
        assertEquals(result, noop.split(number).joinToString(""))
    }

    @Test
    fun testPartOne() {
        val solver = DayEighteen(listOf("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]"))
        assertEquals(4140, solver.partOne())
    }
}
