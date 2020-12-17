package aoc.lib

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {

    fun getManhattanDistance(other: Position) =  abs(x - other.x) + abs(y - other.y)

    fun moveBy(dir: Dir, units: Int): Position = when (dir) {
        Dir.EAST -> Position(x + units, y)
        Dir.WEST -> Position(x - units, y)
        Dir.NORTH -> Position(x, y + units)
        Dir.SOUTH -> Position(x, y - units)
    }

    companion object {
        val ORIGIN = Position(0, 0)
    }
}
