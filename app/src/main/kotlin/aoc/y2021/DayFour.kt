package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayFour(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day04_2021.txt")
    val solver = DayFour(input)
    println(solver.partOne())
}
