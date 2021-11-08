package aoc.y2017
import aoc.lib.Resources.fileAsString

class DayEleven(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day11_2017.txt")
    val solver = DayEleven(input)
    println(solver.partOne())
}
