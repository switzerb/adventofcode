package aoc.y2021

import aoc.lib.MutableStack
import aoc.lib.Resources.fileAsList
import kotlin.math.ceil
import kotlin.math.floor

typealias SnailFish = List<String>

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
            val t = token.toIntOrNull()
            if (t != null && t >= 10) {
                val leftPart = num.subList(0, idx)
                val rightPart = num.subList(idx + 1, num.size)
                val left = floor((t.toDouble() / 2)).toInt()
                val right = ceil((t.toDouble() / 2)).toInt()
                val pair = listOf("[", left.toString(), ",", right.toString(), "]")
                return leftPart + pair + rightPart
            }
        }
        return number
    }

    fun explode(number: SnailFish): SnailFish {
        val num = number.toMutableList()
        var depth = 0
        num.forEachIndexed { idx, token ->
            when (token) {
                "[" -> depth++
                "]" -> depth--
            }
            if (depth == 5) {
                var leftValue = 0
                var rightValue = 0
                val leftIdx = firstIndexOfToLeft(num, idx)
                val rightIdx = firstIndexOfToRight(num, idx + 4)
                if (leftIdx != null) {
                    leftValue = num[idx + 1].toInt() + num[leftIdx].toInt()
                    num[leftIdx] = leftValue.toString()
                }
                if (rightIdx != null) {
                    rightValue = num[idx + 3].toInt() + num[rightIdx].toInt()
                    num[rightIdx] = rightValue.toString()
                }
                return num.subList(0, idx) + "0" + num.subList(idx + 5, num.size)
            }
        }
        return number
    }

    private fun firstIndexOfToLeft(number: SnailFish, startsAt: Int): Int? {
        (startsAt downTo 0).forEach { i ->
            if (number[i].isNumber()) {
                return i
            }
        }
        return null
    }

    private fun firstIndexOfToRight(number: SnailFish, startsAt: Int): Int? {
        (startsAt until number.size).forEach { i ->
            if (number[i].isNumber()) {
                return i
            }
        }
        return null
    }

    fun reduce(first: SnailFish, second: SnailFish): SnailFish {
        var current = add(first, second)
        while (true) {
            val size = current.size
            current = explode(current)
            if (current.size != size) {
                continue
            }
            current = split(current)
            if (current.size == size) {
                break
            }
        }
        return current
    }

    fun sum(): SnailFish = numbers.reduce(this::reduce)

    private fun String.isNumber() = toIntOrNull() != null

    fun getMagnitude(number: SnailFish): Int {
        val ops = MutableStack<String>()
        val nums = MutableStack<Int>()
        number.forEach { token ->
            when {
                token == "[" -> ops.push(token)
                token.isNumber() -> nums.push(token.toInt())
                token == "]" -> {
                    val value = 2 * nums.pop() + 3 * nums.pop()
                    nums.push(value)
                    ops.pop()
                }
            }
        }
        return nums.pop()
    }

    fun partOne(): Int {
        val summed = sum()
        return getMagnitude(summed)
    }

    fun partTwo() {}

    private fun String.toSnailFishNumber() = split("")
}

fun main(args: Array<String>) {
    val input = fileAsList("day18_2021.txt")
    val solver = DayEighteen(input)
    println(solver.partOne())
}
