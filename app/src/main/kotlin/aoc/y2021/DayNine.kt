package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayNine(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day09_2021.txt")
    val solver = DayNine(input)
    println(solver.partOne())
}
