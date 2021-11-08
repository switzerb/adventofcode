package aoc.lib

enum class Direction() {
    NORTH, SOUTH, EAST, WEST;

    fun clockwise(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}
