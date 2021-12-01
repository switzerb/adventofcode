package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayOne(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day01_2021.txt")
    val solver = DayOne(input)
    println(solver.partOne())
}
