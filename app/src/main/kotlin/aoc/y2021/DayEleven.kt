package aoc.y2021
import aoc.lib.Point
import aoc.lib.Resources.fileAsString

typealias OctopusConsortium = Map<Point, Int>

class DayEleven(private val input: String) {

    fun OctopusConsortium.step(): OctopusConsortium {
        val octopii = this.toMutableMap()

        octopii.forEach { point, energy ->
            octopii[point] = energy + 1
        }

        do {
            val flashed = octopii.filterValues { it > 9 }.keys
            flashed.forEach { point -> octopii[point] = 0 }
            flashed.flatMap {
                point ->
                point.neighborsWithDiags()
            }.filter { point ->
                octopii[point] != 0
            }.forEach { point ->
                octopii[point] = octopii.getValue(point) + 1
            }
        } while (flashed.isNotEmpty())
        return octopii
    }

    fun partOne(): Int {
        val octopii: OctopusConsortium = input.toGrid()
        val something = octopii.step()
        println(something)

        return 0
    }

    fun partTwo() {}

    private fun String.toGrid(): Map<Point, Int> {
        return lines().flatMapIndexed { y, row ->
            row.mapIndexed { x, energy -> Point(x, y) to energy.digitToInt() }
        }.toMap()
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day11_2021.txt")
    val solver = DayEleven(input)
    println(solver.partOne())
}
