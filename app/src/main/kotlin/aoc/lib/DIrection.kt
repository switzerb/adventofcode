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

    fun turn(to: String): Direction = when (this) {
        NORTH -> if (to == "R") EAST else WEST
        SOUTH -> if (to == "R") WEST else EAST
        EAST -> if (to == "R") SOUTH else NORTH
        WEST -> if (to == "R") NORTH else SOUTH
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
