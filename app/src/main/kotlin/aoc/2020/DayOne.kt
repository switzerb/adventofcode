package aoc.`2020`

import aoc.DayOne

const val YEAR = 2020

class DayOne2020(val input: String) {

    val numbers = parser()

    private fun parser(): List<Int> {
        val split = input.trim().lines()
        return split.map { it.toInt() }
    }

    fun partOne():Int {
        numbers.forEach {
            val remainder = YEAR - it
            val match = numbers.find { it == remainder }
            if(match != null) {
                return it * remainder
            }
        }
        return -1
    }

    fun partTwo(): Int {
        var next = 0;
        val last = numbers.size-1;
        numbers.forEach { first ->
            next++;
            if(next < numbers.size) {
                for(i in next..last) {
                    val second = numbers[i]
                    val total = first + second
                    if( total < YEAR ) {
                        val third = YEAR - total
                        val match = numbers.find { it == third }
                        if(match != null){
                            return first * second * third
                        }
                    }

                }
            }
        }
        return -1;
    }
}

fun main(args: Array<String>) {
    val cl = DayOne::class.java.classLoader.getResource("day1_2020.txt") ?: return
    val input = cl.readText();
    val solver = DayOne2020(input)
    println("PART ONE: " + solver.partOne()); //514579
    println("PART TWO: " + solver.partTwo()); //230608320
}
