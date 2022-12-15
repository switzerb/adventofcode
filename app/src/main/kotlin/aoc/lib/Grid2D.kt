package aoc.lib

import kotlin.Int.Companion.MAX_VALUE

class Grid2D(val positions: List<Position>? = listOf()) {
    var grid: MutableList<Position> = mutableListOf()

    init {
        if (positions != null) {
            grid.addAll(positions)
        }
    }

    fun maxX(): Int {
        if (grid.isEmpty()) {
            return 0
        }
        var max = 0
        grid.map {
            if (it.x > max) {
                max = it.x
            }
        }
        return max
    }

    fun maxY(): Int {
        if (grid.isEmpty()) {
            return 0
        }
        var max = 0
        grid.map {
            if (it.y > max) {
                max = it.y
            }
        }
        return max
    }

    fun minX(): Int {
        if (grid.isEmpty()) {
            return 0
        }
        var min = MAX_VALUE
        grid.map {
            if (it.x < min) {
                min = it.x
            }
        }
        return if (min == MAX_VALUE) 0 else min
    }

    fun minY(): Int {
        if (grid.isEmpty()) {
            return 0
        }
        var min = MAX_VALUE
        grid.map {
            if (it.y < min) {
                min = it.y
            }
        }
        return if (min == MAX_VALUE) 0 else min
    }

    fun print2D(): String {
        val string = StringBuilder()
        (minY()..maxY()).map { col ->
            (minX()..maxX()).map { row ->
                if (grid.contains(Position(row, col))) {
                    string.append("#")
                } else {
                    string.append(".")
                }
            }
            string.append('\n')
        }
        return string.toString()
    }
}
