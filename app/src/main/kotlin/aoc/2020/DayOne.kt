package aoc.`2020`

import aoc.DayOne

const val YEAR = 2020

class DayOne2020(val input: String) {

    private fun parser(): List<Int> {
        val split = input.trim().lines()
        return split.map { it.toInt() }
    }

    fun partOne():Int {
        val numbers = parser()
        numbers.forEach {
            val remainder = YEAR - it
            val match = numbers.find { it == remainder }
            if(match != null) {
                return it * remainder
            }
        }
        return 0
    }
}

fun main(args: Array<String>) {
    val cl = DayOne::class.java.classLoader.getResource("day1_2020.txt") ?: return
    val input = cl.readText();
    val solver = DayOne2020(input)
    println(solver.partOne());
}
