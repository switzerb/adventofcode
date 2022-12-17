package aoc.lib

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.sign

data class Position(val x: Int, val y: Int) {

    operator fun plus(p: Position) = Position(x + p.x, y + p.y)

    fun getManhattanDistance(other: Position) = abs(x - other.x) + abs(y - other.y)

    fun moveBy(dir: Direction, units: Int = 1): Position = when (dir) {
        Direction.EAST -> Position(x - units, y)
        Direction.WEST -> Position(x + units, y)
        Direction.NORTH -> Position(x, y - units)
        Direction.SOUTH -> Position(x, y + units)
    }

    fun nextTo(): List<Position> =
        listOf(
            Position(x, y + 1),
            Position(x, y - 1),
            Position(x + 1, y),
            Position(x - 1, y)
        )

    fun nextToInclusive(): List<Position> =
        nextTo() + listOf(
            Position(x - 1, y - 1),
            Position(x - 1, y + 1),
            Position(x + 1, y - 1),
            Position(x + 1, y + 1)
        )

    fun isNorthOf(other: Position): Boolean = y < other.y && x == other.x
    fun isSouthOf(other: Position): Boolean = y > other.y && x == other.x
    fun isEastOf(other: Position): Boolean = y == other.y && x < other.x
    fun isWestOf(other: Position): Boolean = y == other.y && x > other.x
    fun isSouthWestOf(other: Position): Boolean = y > other.y && x > other.x
    fun isNorthWestOf(other: Position): Boolean = y < other.y && x > other.x
    fun isSouthEastOf(other: Position): Boolean = y > other.y && x < other.x
    fun isNorthEastOf(other: Position): Boolean = y < other.y && x < other.x

    fun lineTo(other: Position): List<Position> {
        val xDelta = (other.x - x).sign
        val yDelta = (other.y - y).sign
        val steps = maxOf((x - other.x).absoluteValue, (y - other.y).absoluteValue)
        return (1..steps).scan(this) { last, _ -> Position(last.x + xDelta, last.y + yDelta) }
    }

    companion object {
        val ORIGIN = Position(0, 0)

        fun from(input: String): Position =
            input.split(",").let { (x, y) -> Position(x.toInt(), y.toInt()) }
    }
}
