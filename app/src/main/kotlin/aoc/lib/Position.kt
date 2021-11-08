package aoc.lib

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {

    fun getManhattanDistance(other: Position) =  abs(x - other.x) + abs(y - other.y)

    fun moveBy(dir: Direction, units: Int): Position = when (dir) {
        Direction.EAST -> Position(x + units, y)
        Direction.WEST -> Position(x - units, y)
        Direction.NORTH -> Position(x, y + units)
        Direction.SOUTH -> Position(x, y - units)
    }

    companion object {
        val ORIGIN = Position(0, 0)
    }
}
