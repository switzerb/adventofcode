package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Grid2D
import aoc.lib.Position
import aoc.lib.Resources.fileAsString
import kotlin.math.max

/**
 * --- Day 17: Pyroclastic Flow ---
 * https://adventofcode.com/2022/day/17
 */

const val CHAMBER_WIDTH = 7

enum class Shape {
    HLINE,
    DIAMOND,
    DEL,
    VLINE,
    SQUARE
}

data class Rock(
    val shape: Shape,
    var coords: MutableList<Position> = mutableListOf()
) {
    var tall = 0

    init {
        when (shape) {
            Shape.HLINE -> {
                this.tall = 1
                this.coords = mutableListOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(2, 0),
                    Position(3, 0)
                )
            }
            Shape.DIAMOND -> {
                this.tall = 3
                this.coords = mutableListOf(
                    Position(1, 0),
                    Position(0, -1),
                    Position(1, -1),
                    Position(2, -1),
                    Position(1, -2)
                )
            }
            Shape.DEL -> {
                this.tall = 3
                this.coords = mutableListOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(2, 0),
                    Position(2, -1),
                    Position(2, -2)
                )
            }
            Shape.VLINE -> {
                this.tall = 4
                this.coords = mutableListOf(
                    Position(0, 0),
                    Position(0, -1),
                    Position(0, -2),
                    Position(0, -3)
                )
            }
            Shape.SQUARE -> {
                this.tall = 2
                this.coords = mutableListOf(
                    Position(0, 0),
                    Position(1, 0),
                    Position(0, -1),
                    Position(1, -1)
                )
            }
        }
    }

    fun moveTo(p: Position): Rock {
        val newPositions = buildList {
            for (pos in this@Rock.coords) {
                add(pos.plus(p))
            }
        }.toMutableList()
        this.coords.clear()
        this.coords.addAll(newPositions)
        return this
    }

    fun move(dir: Direction, unit: Int = 1) =
        copy(shape = shape, coords = this.coords.map { it.moveBy(dir, unit) }.toMutableList())

    fun down(unit: Int = 1) =
        move(Direction.SOUTH, unit)

    fun right(unit: Int = 1) =
        move(Direction.EAST, unit)

    fun left(unit: Int = 1) =
        move(Direction.WEST, unit)
}

// starts seven wide with a bottom, four deep
data class Chamber(val atRest: Grid2D = Grid2D()) {
    fun canMove(rock: Rock) =
        rock.coords.all {
            it.x in 0..6 &&
                it.y <= 0 &&
                !atRest.grid.contains(it)
        }

    fun draw(rock: Rock? = null) =
        buildString {
            for (y in atRest.minY()..0) {
                for (x in -1..CHAMBER_WIDTH) {
                    if (x < 0 || x == CHAMBER_WIDTH) append('|')
                    else if (atRest.grid.contains(Position(x, y))) append('#')
                    else if (rock == null) append('.')
                    else if (rock.coords.contains(Position(x, y))) append('@')
                    else append('.')
                }
                append('\n')
            }
            append('+')
            append("-".repeat(CHAMBER_WIDTH))
            append('+')
            append('\n')
        }
}

class DaySeventeen(private val input: String) {
    private val rockSequence = listOf(
        Rock(shape = Shape.HLINE),
        Rock(shape = Shape.DIAMOND),
        Rock(shape = Shape.DEL),
        Rock(shape = Shape.VLINE),
        Rock(shape = Shape.SQUARE)
    )

    val jets = input.toCharArray()

    fun partOne(rounds: Int): Int {
        val chamber = Chamber()
        var rockIdx = 0
        var jetIdx = 0
        var height = 0

        repeat(rounds) {
            val nextRock = rockSequence[rockIdx++ % Shape.values().size]
            var rock = nextRock.moveTo(Position(2, height - nextRock.tall - 2))

            var canMove = true

            while (canMove) {
                val motion = jets[jetIdx++ % jets.size]

                rock = when (motion) {
                    '<' -> if (chamber.canMove(rock)) rock.move(Direction.EAST) else rock
                    '>' -> if (chamber.canMove(rock)) rock.move(Direction.WEST) else rock
                    else -> throw UnsupportedOperationException("Motion not supported")
                }

                if (chamber.canMove(rock)) {
                    rock = rock.move(Direction.SOUTH)
                } else {
                    chamber.atRest.addTo(rock.coords.toList())
                    height = max(height, -rock.tall)
                    canMove = false
                }
                println(chamber.draw(rock))
            }
        }
        return height
    }

    fun partTwo(): Long {
        return 0L
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day17_2022.txt")
    val solver = DaySeventeen(input)
    println(solver.partOne(rounds = 2022))
}
