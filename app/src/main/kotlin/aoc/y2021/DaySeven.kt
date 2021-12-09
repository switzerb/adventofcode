package aoc.y2021
import aoc.lib.Resources.fileAsString

class DaySeven(private val input: String) {

    fun partOne(): String {
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day07_2021.txt")
    val solver = DaySeven(input)
    println(solver.partOne())
}
