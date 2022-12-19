package aoc.y2022

import aoc.lib.Resources.fileAsString
import aoc.lib.Vector

/**
 * --- Day 18: Boiling Boulders ---
 * https://adventofcode.com/2022/day/18
 */

const val SIDES = 6

class DayEighteen(private val input: String) {

    private val cubes: Set<Vector> = input
        .split('\n')
        .map { coord ->
            Vector.from(coord)
        }.toSet()

    fun partOne(): Int {
        return cubes.map { cube ->
            SIDES - cube.neighbors()
                .count { neighbor -> neighbor in cubes }
        }.sum()
    }

    fun partTwo(): Int {
        return cubes.filter { cube ->
            cube.neighbors().size == 6
        }.map { cube ->
            SIDES - cube.neighbors()
                .count { neighbor -> neighbor in cubes }
        }.sum()
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day18_2022.txt")
    val solver = DayEighteen(input)
    println(solver.partOne())
}
