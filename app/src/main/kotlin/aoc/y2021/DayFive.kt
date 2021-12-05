package aoc.y2021
import aoc.lib.Resources.fileAsList
import aoc.lib.Vector

class DayFive(private val input: List<String>) {

    fun parse(): List<Vector> {
        return input
            .flatMap { line ->
                line.split(" -> ").map { point ->
                    val coords = point.split(",")
                    Vector(coords.first().toInt(), coords.last().toInt())
                }
            }
            .windowed(2, 2)
            .flatMap { line ->
                expandLines(line.first(), line.last())
            }
    }

    fun expandLines(p1: Vector, p2: Vector): List<Vector> {
        if (p1.x() == p2.x()) {
            if (p1.y() < p2.y()) {
                return (p1.y()..p2.y()).map { point ->
                    Vector(p1.x(), point)
                }
            } else {
                return (p1.y() downTo p2.y()).map { point ->
                    Vector(p1.x(), point)
                }
            }
        }
        if (p1.y() == p2.y()) {
            if (p1.x() < p2.x()) {
                return (p1.x()..p2.x()).map { point ->
                    Vector(point, p1.y())
                }
            } else {
                return (p1.x() downTo p2.x()).map { point ->
                    Vector(point, p1.y())
                }
            }
        }
        return emptyList()
    }

    fun partOne(): Int {
        val vents = parse()
        val hist = mutableMapOf<Vector, Int>()

        vents.forEach { point ->
            hist[point] = hist.getOrDefault(point, 0) + 1
        }
        return hist.filterValues { count ->
            count >= 2
        }.size
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsList("day05_2021.txt")
    val solver = DayFive(input)
    println(solver.partOne())
}
