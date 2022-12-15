package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Position
import aoc.lib.Resources.fileAsString

class Cave(
    val rocks: MutableList<Position>,
    val sandAtRest: MutableList<Position> = mutableListOf(),
    var falling: Position? = null
) {
    fun maxX(): Int {
        if (rocks.isEmpty()) return 0
        var max = 0
        rocks.map {
            if (it.x > max) {
                max = it.x
            }
        }
        return max
    }

    fun maxY(): Int {
        if (rocks.isEmpty()) return 0
        var max = 0
        rocks.map {
            if (it.y > max) {
                max = it.y
            }
        }
        return max
    }

    fun minX(): Int {
        if (rocks.isEmpty()) return 0
        var min = Int.MAX_VALUE
        rocks.map {
            if (it.x < min) {
                min = it.x
            }
        }
        return if (min == Int.MAX_VALUE) 0 else min
    }

    fun minY(): Int {
        if (rocks.isEmpty()) return 0
        var min = Int.MAX_VALUE
        rocks.map {
            if (it.y < min) {
                min = it.y
            }
        }
        return if (min == Int.MAX_VALUE) 0 else min
    }

    override fun toString(): String {
        val string = StringBuilder()
        (minY()..maxY()).map { y ->
            (minX()..maxX()).map { x ->
                if (rocks.contains(Position(x, y))) {
                    string.append("#")
                } else if (sandAtRest.contains(Position(x, y))) {
                    string.append('o')
                } else if (falling == Position(x, y)) {
                    string.append('+')
                } else {
                    string.append(".")
                }
            }
            string.append('\n')
        }
        return string.toString()
    }
}

class DayFourteen(private val input: String) {

    val cave = Cave(
        rocks = mutableListOf(
            Position(498, 4),
            Position(498, 5),
            Position(498, 6),
            Position(497, 6),
            Position(496, 6),
            Position(503, 4),
            Position(502, 4),
            Position(502, 5),
            Position(502, 6),
            Position(502, 7),
            Position(502, 8),
            Position(502, 9),
            Position(501, 9),
            Position(500, 9),
            Position(499, 9),
            Position(498, 9),
            Position(497, 9),
            Position(496, 9),
            Position(495, 9),
            Position(494, 9)
        )
    )

    fun canMoveDown(sand: Position): Boolean = !cave.rocks.contains(Position(sand.x, sand.y + 1))

    fun partOne(): Int {
        var count = 0
        var current = Position(500, 0)
        println(cave)
//        while (sand.y < cave.maxY()) {
        while (canMoveDown(current)) {
            cave.falling = current.moveBy(Direction.SOUTH, 1)
            current = cave.falling!!
            println(cave)
        }
//        }
        // while sand Y point is smaller than max Y
        // while sand can move,
        // move it
        // start new sand
        return count
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day14_2022.txt")
    val start = Position(500, 0)
    val solver = DayFourteen(input)
    println(solver.partOne())
}
