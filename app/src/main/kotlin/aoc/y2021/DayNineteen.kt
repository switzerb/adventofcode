package aoc.y2021
import aoc.lib.Resources.fileAsString

class DayNineteen(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day19_2021.txt")
    val solver = DayNineteen(input)
    println(solver.partOne())
}
