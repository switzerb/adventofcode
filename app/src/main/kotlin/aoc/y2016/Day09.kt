package aoc.y2016

import aoc.lib.Resources

/*
--- Day 9: Explosives in Cyberspace ---
https://adventofcode.com/2016/day/9
*/

class Day09(private val input: String) {

    fun partOne(): Int {
        /*
        ADVENT
        A(1x5)BC
        (3x3)XYZ
        (6x1)(1x3)A
        it's a marker unless it follows another marker
         */
        val MARKLEN = 5
        var idx = 0
        var runningLength = input.length
        for (char in input) {
            when (char) {
                '(' -> {
                    if (idx != 0 && input[idx - 1] == ')') {
                        break
                    }
                    val charCount = input[idx + 1].digitToInt()
                    val reps = input[idx + 3].digitToInt()
                    runningLength += (charCount * reps)
                    runningLength -= (MARKLEN + charCount)
                    idx += MARKLEN
                }
            }
            idx++
        }

        return runningLength
    }
}

fun main(args: Array<String>) {
    val input = Resources.fileAsString("2016/input_day09.txt")
    val solver = Day09(input)
    println(solver.partOne())
}
