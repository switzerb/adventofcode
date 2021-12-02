package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayTwo(private val input: String) {

    fun partOne(): String {
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day02_2021.txt")
    val solver = DayTwo(input)
    println(solver.partOne())
}
