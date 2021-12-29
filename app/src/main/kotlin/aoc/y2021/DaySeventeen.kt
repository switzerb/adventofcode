package aoc.y2021

import aoc.lib.Point
import aoc.lib.Resources.fileAsString
import kotlin.math.abs

data class Target(val xRange: IntRange, val yRange: IntRange) {
    val xMax = xRange.last
    val yMax = yRange.first
}

class DaySeventeen(private val input: String) {

    private val target = input.parse()

    private fun drag(x: Int): Int = if (x == 0) 0 else x - 1
    private fun gravity(y: Int): Int = y - 1

    private fun inTargetArea(point: Point): Boolean = point.x in target.xRange && point.y in target.yRange

    fun launch(initial: Point): Point? {
        val position = Point.ORIGIN
        var cp = position
        var cv = initial
        while (true) {
            cp = Point(cp.x + cv.x, cp.y + cv.y)
            cv = Point(drag(cv.x), gravity(cv.y))
            if (inTargetArea(cp)) {
                return initial
            }
            if (cp.x > target.xMax || cp.y < target.yMax) {
                return null
            }
        }
    }

    fun partOne(): Int = (abs(target.yMax) - 1 downTo 0).sum()

    fun partTwo(): Int {
        val potentials = mutableListOf<Point?>()
        (abs(target.yMax) downTo target.yMax).map { y ->
            (0..target.xMax).map { x ->
                val result = launch(Point(x, y))
                if (result != null) {
                    potentials.add(result)
                }
            }
        }
        return potentials.size
    }

    private fun String.parse(): Target {
        val (x, y) = this.split(",").map { coord ->
            val (min, max) = coord
                .trim()
                .drop(2)
                .split("..").map { n ->
                    n.toInt()
                }
            IntRange(min, max)
        }
        return Target(xRange = x, yRange = y)
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day17_2021.txt")
    val solver = DaySeventeen(input)
    println(solver.partOne())
    println(solver.partTwo())
}
