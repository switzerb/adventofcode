package aoc.y2021

import aoc.lib.Resources.fileAsList
import kotlin.math.ceil
import kotlin.math.floor

typealias SnailFish = List<Char>

// Day 18: Snailfish
// https://adventofcode.com/2021/day/18
class DayEighteen(private val input: List<String>) {

    val numbers = input.map { it.toSnailFishNumber() }

    fun add(first: SnailFish, second: SnailFish): SnailFish {
        val str = "[" + first.joinToString("") + "," + second.joinToString("") + "]"
        return str.toSnailFishNumber()
    }

    fun split(number: SnailFish): SnailFish {
        val num = number.toMutableList()
        num.forEachIndexed { idx, token ->
            if (
                idx < num.size - 1 &&
                token.isDigit() &&
                num[idx + 1].isDigit()
            ) {
                val leftPart = num.subList(0, idx)
                val rightPart = num.subList(idx + 2, num.size)
                val value = (num[idx].toString() + num[idx + 1].toString()).toInt()
                val left = floor((value.toDouble() / 2)).toInt()
                val right = ceil((value.toDouble() / 2)).toInt()
                val pair = listOf('[', left.digitToChar(), ',', right.digitToChar(), ']')
                return leftPart + pair + rightPart
            }
        }
        return number
    }

    fun explode(number: SnailFish): SnailFish {
        var num = number.toList()
        var depth = 0
        num.forEachIndexed { idx, token ->
            when (token) {
                '[' -> depth++
                ']' -> depth--
            }
            if (depth == 5) {
                var leftValue = 0
                var rightValue = 0
                val leftIdx = firstIndexOfToLeft(num, idx)
                val rightIdx = firstIndexOfToRight(num, idx + 4)
                if (leftIdx != null) {
                    leftValue = num[idx + 1].digitToInt() + num[leftIdx].digitToInt()
                    val adds = leftValue.toString().toList()
                    num = num.subList(0, leftIdx) + adds + num.subList(leftIdx + 1, num.size)
                }
                if (rightIdx != null) {
                    rightValue = num[idx + 3].digitToInt() + num[rightIdx].digitToInt()
                    val adds = rightValue.toString().toList()
                    num = num.subList(0, rightIdx) + adds + num.subList(rightIdx + 1, num.size)
                }
                return num.subList(0, idx) + '0' + num.subList(idx + 5, num.size)
            }
        }
        return number
    }

    private fun firstIndexOfToLeft(number: SnailFish, startsAt: Int): Int? {
        (startsAt downTo 0).forEach { i ->
            if (number[i].isDigit()) {
                return i
            }
        }
        return null
    }

    private fun firstIndexOfToRight(number: SnailFish, startsAt: Int): Int? {
        (startsAt until number.size).forEach { i ->
            if (number[i].isDigit()) {
                return i
            }
        }
        return null
    }

    fun partOne(): Int {
        var current = add(numbers[0], numbers[1])
        println(current)
        var again = true
        do {
            val init = current.size
            do {
                val s = current.size
                current = explode(current)
                println(current)
            } while (current.size != s)

            do {
                val s = current.size
                current = split(current)
                println(current)
            } while (current.size != s)

            if (current.size == init) {
                again = false
            }
        } while (again)

        println(current)
        return 0
    }

    fun partTwo() {}

    private fun String.toSnailFishNumber() = toList()
}

fun main(args: Array<String>) {
    val input = fileAsList("day18_2021.txt")
    val solver = DayEighteen(input)
    println(solver.partOne())
}
