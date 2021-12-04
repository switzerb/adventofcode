package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayThree(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day03_2021.txt")
    val solver = DayThree(input)
    println(solver.partOne())
}
