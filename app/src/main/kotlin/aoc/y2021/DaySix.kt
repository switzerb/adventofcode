package aoc.y2021
import aoc.lib.Resources.fileAsString

class DaySix(private val input: String) {

    fun partOne(): String {
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day06_2021.txt")
    val solver = DaySix(input)
    println(solver.partOne())
}
