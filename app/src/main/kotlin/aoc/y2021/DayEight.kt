package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayEight(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day08_2021.txt")
    val solver = DayEight(input)
    println(solver.partOne())
}
