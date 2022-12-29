package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Grid2D
import aoc.lib.Position
import aoc.lib.Resources.fileAsString

typealias Elf = Position

class DayTwentyThree(private val input: String) {

    val orderedDirections = listOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)
    var orderIdx = 0

    val directionSet: Map<Direction, Set<Position>> = mapOf(
        Direction.NORTH to setOf(Position(0, -1), Position(-1, -1), Position(1, -1)),
        Direction.SOUTH to setOf(Position(0, 1), Position(-1, 1), Position(1, 1)),
        Direction.WEST to setOf(Position(-1, 0), Position(-1, -1), Position(-1, 1)),
        Direction.EAST to setOf(Position(1, 0), Position(1, -1), Position(1, 1))
    )

    // five elves from example
    val field = Grid2D(
        listOf(
            Elf(0, 0),
            Elf(1, 0),
            Elf(0, 1),
            Elf(0, 3),
            Elf(1, 3)
        )
    )

    fun proposeMove(elf: Elf): Direction? {
        val adjacent = elf.nextToInclusive()

        // if there are no elves adjacent, do nothing
        if (field.grid.any { it in adjacent }) {
            return null

            // otherwise look in order and propose the first step where there isn't another elf
        } else {
            for (i in 0..3) {
                val idx = ((orderIdx + i) % orderedDirections.size)
                val direction = orderedDirections[idx]
                if (directionSet[direction]!!.none { pos -> pos + elf in field.grid }) {
                    return direction
                }
            }
        }
        return null
    }

    fun round() {
        val proposals = mutableListOf<Direction>()
        for (elf in field.grid) {
            val dir = proposeMove(elf)
            if (dir != null) {
                proposals.add(dir)
            }
        }
        // all elves make a proposal to move
        // get adjacent ground if there is an elf in it
        // if there are
        orderIdx++
    }

    fun partOne(): String {
        return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day23_2022.txt")
    val solver = DayTwentyThree(input)
    println(solver.partOne())
}
