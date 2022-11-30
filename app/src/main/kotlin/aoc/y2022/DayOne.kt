package aoc.y2022

import aoc.lib.Resources.fileAsString

class DayOne(private val input: String) {

    fun partOne(): String {
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day01_2022.txt")
    val solver = DayOne(input)
    println(solver.partOne())
}
