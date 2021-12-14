package aoc.y2021
import aoc.lib.Resources.fileAsList
import aoc.lib.Resources.fileAsString

data class Point(val x: Int, val y: Int) {

    fun neighbors(): List<Point> =
        listOf(
            Point(x, y + 1),
            Point(x, y - 1),
            Point(x + 1, y),
            Point(x - 1, y)
        )

    fun allNeighbors(): List<Point> =
        neighbors() + listOf(
            Point(x - 1, y - 1),
            Point(x - 1, y + 1),
            Point(x + 1, y - 1),
            Point(x + 1, y + 1)
        )
}

class DayNine(input: List<String>) {

    private fun parseInput(input: List<String>): Array<IntArray> {
        return input.map { row ->
            row.map { it.digitToInt() }.toIntArray()
        }.toTypedArray()
    }

    private val caves: Array<IntArray> = parseInput(input)

    private fun getBasinSize(point: Point): Int {
        val visited = mutableSetOf(point)
        val queue = mutableListOf(point)
        while (queue.isNotEmpty()) {
            val newNeighbors = queue.removeFirst()
                .actualNeighbors()
                .filter { it !in visited }
                .filter { caves[it] != 9 }
            visited.addAll(newNeighbors)
            queue.addAll(newNeighbors)
        }
        return visited.size
    }

    fun Point.actualNeighbors(): List<Point> =
        neighbors().filter { it in caves }

    fun Array<IntArray>.findLowPoints(): List<Point> {
        return flatMapIndexed { y, row ->
            row.mapIndexed { x, height ->
                Point(x, y).takeIf { point ->
                    point.actualNeighbors().map { caves[it] }.all { height < it }
                }
            }.filterNotNull()
        }
    }

    operator fun Array<IntArray>.get(point: Point): Int {
        return this[point.y][point.x]
    }

    operator fun Array<IntArray>.contains(point: Point): Boolean =
        point.y in this.indices && point.x in this[point.y].indices

    /**
     * [2,1,9,9,9,4,3,2,1,0
     *  3,9,8,7,8,9,4,9,2,1
     *  9,8,5,6,7,8,9,8,9,2
     *  8,7,6,7,8,9,6,7,8,9
     *  9,8,9,9,9,6,5,6,7,8]
     */
    fun partOne(): Int {
        return caves.findLowPoints().sumOf {
            caves[it] + 1
        }
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day09_2021.txt")
    val solver = DayNine(fileAsList(input))
    println(solver.partOne())
}
