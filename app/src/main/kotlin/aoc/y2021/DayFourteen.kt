package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayFourteen(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day14_2021.txt")
    val solver = DayFourteen(input)
    println(solver.partOne())
}
