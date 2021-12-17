package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayEleven(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day11_2021.txt")
    val solver = DayEleven(input)
    println(solver.partOne())
}
