package aoc.y2021
import aoc.lib.Resources.fileAsString

class DaySixteen(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day16_2021.txt")
    val solver = DaySixteen(input)
    println(solver.partOne())
}
