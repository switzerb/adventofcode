package aoc.y2021

import aoc.lib.Resources.fileAsString

typealias SnailFish = List<Char>

// Day 18: Snailfish
// https://adventofcode.com/2021/day/18
class DayEighteen(private val input: String) {

    fun add(first: SnailFish, second: SnailFish): SnailFish {
        val str = "[" + first.joinToString("") + "," + second.joinToString("") + "]"
        return str.toSnailFishNumber()
    }

    fun explode(number: SnailFish): SnailFish? {
        val num = number.toMutableList()
        var depth = 0
        num.forEachIndexed { idx, token ->
            when (token) {
                '[' -> depth++
                ']' -> depth--
            }
            if (depth == 5) {
                val leftPart = num.subList(0, idx)
                val rightPart = num.subList(idx + 5, num.size)
                var leftValue = 0
                var rightValue = 0
                val leftIdx = firstIndexOfToLeft(num, idx)
                val rightIdx = firstIndexOfToRight(num, idx + 4)
                if (leftIdx != null) {
                    leftValue = num[idx + 1].digitToInt() + num[leftIdx].digitToInt()
                    num[leftIdx] = leftValue.digitToChar()
                }
                if (rightIdx != null) {
                    rightValue = num[idx + 3].digitToInt() + num[rightIdx].digitToInt()
                    num[rightIdx] = rightValue.digitToChar()
                }
                return leftPart + '0' + rightPart
            }
        }
        return null
    }

    fun firstIndexOfToLeft(number: SnailFish, startsAt: Int): Int? {
        (startsAt downTo 0).forEach { i ->
            if (number[i].isDigit()) {
                return i
            }
        }
        return null
    }

    fun firstIndexOfToRight(number: SnailFish, startsAt: Int): Int? {
        (startsAt until number.size).forEach { i ->
            if (number[i].isDigit()) {
                return i
            }
        }
        return null
    }

    fun partOne(): String {
        // result = add one and two
        // add result to three
        // add result of above to four
        return input
    }

    fun partTwo() {}

    private fun String.toSnailFishNumber() = toList()
}

fun main(args: Array<String>) {
    val input = fileAsString("day18_2021.txt")
    val solver = DayEighteen(input)
    println(solver.partOne())
}

// fun String.toSFNum(): SFNum {
//    val stack = MutableStack<SFNum>()
//    val split = toCharArray()
//    split.forEach { c ->
//        when {
//            c.isDigit() -> {
//                stack.push(SFNum(value = c.digitToInt()))
//            }
//            c == ']' -> {
//                val right = stack.pop()
//                val left = stack.pop()
//                stack.push(
//                    SFNum(
//                        left = left,
//                        right = right,
//                    )
//                )
//            }
//        }
//    }
//    return stack.pop()
// }
