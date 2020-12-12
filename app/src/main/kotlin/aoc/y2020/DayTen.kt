package aoc.y2020

import aoc.lib.Resources.fileAsInt

class DayTen2020(val input: List<Int>) {

    fun partOne() :Int {

        val deviceAdapterMax = input.max()!! + 3
        val adapters = input.plus(0).plus(deviceAdapterMax).sorted()

        val groupedAdapters = adapters
            .zipWithNext()
            .map { it.second - it.first}
            .groupingBy { it }
            .eachCount()

        val ones = groupedAdapters[1]
        val threes = groupedAdapters[3]
        return ones!! * threes!!
    }
}

fun main(args: Array<String>) {
    val solver = DayTen2020(fileAsInt("day10_2020.txt"))
    println(solver.partOne()) //2263
}
