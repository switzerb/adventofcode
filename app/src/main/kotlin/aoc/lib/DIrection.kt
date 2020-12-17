package aoc.lib

enum class Dir() {
    NORTH, SOUTH, EAST, WEST;

    fun clockwise(): Dir = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}
