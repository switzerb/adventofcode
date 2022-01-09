package aoc.y2021

import aoc.lib.MutableStack
import aoc.lib.Resources.fileAsString

data class SFNum(
    val left: SFNum?,
    val right: SFNum?,
    val value: Int?,
) {
    constructor(value: Int) : this(null, null, value)
    constructor(left: SFNum, right: SFNum) : this(left, right, null)

    fun SFNum.add(other: SFNum): SFNum {
        val added = SFNum(
            left = this,
            right = other
        )
        return added.reduced()
    }

    fun SFNum.reduced(): SFNum {
        // do while there are no more actions to take
        // any explodable numbers?
        // any splitable numbers?
        return this
    }

    // traverse to find regular numbers on left of tree?

    fun traverse(depth: Int = 0): Int {
        if (depth == 5) {
            // this is how we know it needs to be exploded
            return value!!
        }
        val left = left?.traverse(depth = depth + 1)
        val right = right?.traverse(depth = depth + 1)
        return 0
    }
}

// Day 18: Snailfish
// https://adventofcode.com/2021/day/18
class DayEighteen(private val input: String) {

    // If any pair is nested inside four pairs, the leftmost such pair explodes.
    // If any regular number is 10 or greater, the leftmost such regular number splits.
    val numbers = listOf<SFNum>()

    fun partOne(): String {
        // result = add one and two
        // add result to three
        // add result of above to four
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day18_2021.txt")
    val solver = DayEighteen(input)
    println(solver.partOne())
}

fun String.toSFNum(): SFNum {
    val stack = MutableStack<SFNum>()
    val split = toCharArray()
    split.forEach { c ->
        when {
            c.isDigit() -> {
                stack.push(SFNum(value = c.digitToInt()))
            }
            c == ']' -> {
                val right = stack.pop()
                val left = stack.pop()
                stack.push(
                    SFNum(
                        left = left,
                        right = right,
                    )
                )
            }
        }
    }
    return stack.pop()
}
