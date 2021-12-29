package aoc.y2021

import aoc.lib.Point
import aoc.lib.Resources.fileAsString
import kotlin.math.abs

typealias Target = IntRange

class DaySeventeen(private val input: String) {

    private val target = input.parse()

    private fun drag(x: Int): Int = if (x == 0) 0 else x - 1
    private fun gravity(y: Int): Int = y - 1

    private fun inTargetArea(point: Point): Boolean = point.x in target.first && point.y in target.second

    fun step(): Point {
        val position = Point.ORIGIN
        val velocity = Point(7, 2)
        var cp = position
        var cv = velocity
        var inTargetArea = false
        while (!inTargetArea) {
            cp = Point(cp.x + cv.x, cp.y + cv.y)
            cv = Point(drag(cv.x), gravity(cv.y))
            inTargetArea = inTargetArea(cp)
        }
        return cp
    }

    fun partOne(): Int = (abs(target.second.first) - 1 downTo 0).sum()

    fun partTwo() {}

    private fun String.parse(): Pair<Target, Target> {
        val targets = "x=20..30, y=-10..-5".split(",").map { coord ->
            val (min, max) = coord
                .trim()
                .drop(2)
                .split("..").map { n ->
                    n.toInt()
                }
            Target(min, max)
        }
        return Pair(targets[0], targets[1])
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day17_2021.txt")
    val solver = DaySeventeen(input)
    println(solver.partOne())
}
