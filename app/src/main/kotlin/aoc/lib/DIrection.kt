package aoc.lib

import java.lang.UnsupportedOperationException

enum class Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    fun clockwise(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    companion object {
        fun from(dir: String): Direction {
            return when (dir) {
                "R" -> WEST
                "L" -> EAST
                "U" -> NORTH
                "D" -> SOUTH
                else -> throw UnsupportedOperationException("Direction not supported")
            }
        }
    }
}
