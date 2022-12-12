package aoc.y2022

import aoc.lib.Point
import aoc.lib.Resources.fileAsList
import java.util.*

class DayTwelve(private val input: List<String>) {

    private val elevationMap: ElevationMap = parse(input)

    class ElevationMap(
        val elevations: Map<Point, Int>,
        val start: Point,
        val end: Point
    ) {
        private data class Effort(
            val point: Point,
            val cost: Int
        ) : Comparable<Effort> {
            override fun compareTo(other: Effort): Int =
                this.cost.compareTo(other.cost)
        }

        private fun canMove(from: Int, to: Int) = to - from <= 1

        fun shortestPath(
            begin: Point,
            isEndGoal: (Point) -> Boolean
        ): Int {
            val visited = mutableSetOf<Point>()
            val pq = PriorityQueue<Effort>()
            pq.add(Effort(begin, 0))

            while (pq.isNotEmpty()) {
                val next = pq.poll()

                if (next.point !in visited) {
                    visited.add(next.point)

                    // get neighbors on the map that we can move to
                    val neighbors = next.point.cardinalNeighbors()
                        .filter { it in elevations }
                        .filter {
                            canMove(
                                from = elevations.getValue(next.point),
                                to = elevations.getValue(it)
                            )
                        }

                    // if any of our neighbors are the goal, return the cost
                    if (neighbors.any { isEndGoal(it) }) return next.cost + 1
                    pq.addAll(neighbors.map { Effort(it, next.cost + 1) })
                }
            }
            throw IllegalStateException("No valid path found.")
        }
    }

    private fun parse(input: List<String>): ElevationMap {
        var start: Point? = null
        var end: Point? = null
        val elevations = input.flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                val current = Point(x, y)
                current to when (char) {
                    'S' -> 0.apply { start = current }
                    'E' -> 25.apply { end = current }
                    else -> char - 'a'
                }
            }
        }.toMap()
        return ElevationMap(elevations, start!!, end!!)
    }

    /**
     * Breadth First Search (BFS), priority queue
     *
     * queue that keeps its contents in order so that first item is always "best"
     *
     * For each point on grid:
     *  - calculate how many steps to arrive
     *  - find all neighbors
     *  - add neighbors to the queue with effort cost++
     *  - exit when goal found
     */
    fun partOne(): Int = elevationMap.shortestPath(
        begin = elevationMap.start,
        isEndGoal = { it == elevationMap.end }
    )

    fun partTwo(): Int = elevationMap.shortestPath(
        begin = elevationMap.end,
        isEndGoal = { elevationMap.elevations[it] == 0 }
    )
}

fun main(args: Array<String>) {
    val input = fileAsList("2022/day12_2022.txt")
    val solver = DayTwelve(input)
    println(solver.partOne())
    println(solver.partTwo())
}
