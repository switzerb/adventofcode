package aoc.y2020

import aoc.lib.Resources.fileAsString

class DayThirteen2020(val depart: Int, val input: List<String>) {

    fun partOne(): Int {
        val schedule = input.filter { it != "x" }.map { it.toInt() }
        (0..depart+100).fold(emptyList<Int>()) { a, tick ->
            val earliest = a.find { bus -> bus >= depart }
            if(earliest != null) {
                val bus = schedule[a.indexOf(earliest)]
                return (earliest - depart) * bus
            }
            schedule.map { it * tick }
        }
        return 0
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day13_2020.txt").trim().split(",")
    val solver = DayThirteen2020(1011416, input)
    println(solver.partOne()) //642255
}
