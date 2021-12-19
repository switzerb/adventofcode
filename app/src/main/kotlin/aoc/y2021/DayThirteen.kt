package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayThirteen(private val input: String) {

    fun partOne(): String {
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day13_2021.txt")
    val solver = DayThirteen(input)
    println(solver.partOne())
}
