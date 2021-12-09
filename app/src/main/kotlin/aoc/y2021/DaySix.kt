package aoc.y2021

import aoc.lib.Resources.fileAsString

class DaySix(private val input: String) {
    val lanternfish: LongArray = parseInput(input)

    fun partOne(): Long =
        run(80)

    fun partTwo(): Long =
        run(256)

    private fun run(days: Int): Long {
        repeat(days) {
            val progression = progressGeneration(lanternfish)
            progression[6] += progression[8]
        }
        return lanternfish.sum()
    }

    // array index is day
    private fun progressGeneration(lanternfish: LongArray): LongArray {
        val first = lanternfish.first()
        lanternfish.copyInto(lanternfish, startIndex = 1)
        lanternfish[lanternfish.lastIndex] = first
        return lanternfish
    }

    private fun parseInput(input: String): LongArray =
        LongArray(9).apply {
            input
                .split(",")
                .map { it.toInt() }
                .forEach { this[it] += 1L }
        }
}

fun main(args: Array<String>) {
    val input = fileAsString("day06_2021.txt")
    val solver = DaySix(input)
    println(solver.partOne()) //390011
    println(solver.partTwo()) //1852978735751314
}
