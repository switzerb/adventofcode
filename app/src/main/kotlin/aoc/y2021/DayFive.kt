package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayFive(private val input: String) {

    fun partOne(): String {
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day05_2021.txt")
    val solver = DayFive(input)
    println(solver.partOne())
}
