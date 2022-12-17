package aoc.y2022

import aoc.lib.Grid2D
import aoc.lib.Position
import aoc.lib.Resources.fileAsString

/**
 * --- Day 17: Pyroclastic Flow ---
 * https://adventofcode.com/2022/day/17
 */

enum class Shape {
    HLINE,
    DIAMOND,
    DEL,
    VLINE,
    SQUARE
}

data class Rock(val shape: Shape) {
    var positions: MutableList<Position> = mutableListOf()

    constructor(shape: Shape, coords: MutableList<Position>) : this(shape) {
        positions.addAll(coords)
    }

    init {
        positions = when (shape) {
            Shape.HLINE -> mutableListOf(
                Position(0, 0),
                Position(1, 0),
                Position(2, 0),
                Position(3, 0)
            )
            Shape.DIAMOND -> mutableListOf(
                Position(1, 0),
                Position(0, -1),
                Position(1, -1),
                Position(2, -1),
                Position(1, -2)
            )
            Shape.DEL -> mutableListOf(
                Position(0, 0),
                Position(1, 0),
                Position(2, 0),
                Position(2, -1),
                Position(2, -2)
            )
            Shape.VLINE -> mutableListOf(
                Position(0, 0),
                Position(0, -1),
                Position(0, -2),
                Position(0, -3)
            )
            Shape.SQUARE -> mutableListOf(
                Position(0, 0),
                Position(1, 0),
                Position(0, -1),
                Position(1, -1)
            )
        }
    }

    fun moveTo(p: Position): Rock {
        val newPositions = buildList {
            for (pos in positions) {
                add(pos.plus(p))
            }
        }.toMutableList()
        this.positions.clear()
        this.positions.addAll(newPositions)
        return this
    }

    fun moveRight(): Rock = this.moveTo(Position(1, 0))
    fun moveLeft(): Rock = this.moveTo(Position(-1, 0))
    fun moveDown(): Rock = this.moveTo(Position(0, 1))

    fun getRockBottomEdge(): Set<Position> {
        return when (shape) {
            Shape.HLINE -> positions.toSet()
            Shape.DIAMOND -> setOf(positions[0])
            Shape.DEL -> setOf(positions[0], positions[1], positions[2])
            Shape.VLINE -> setOf(positions[0])
            Shape.SQUARE -> setOf(positions[0], positions[1])
        }
    }
}

// starts seven wide with a bottom, four deep
data class Chamber(val rocks: Grid2D = Grid2D()) {
    fun canMoveLeft(rock: Rock): Boolean {
        return rock.positions.none { position -> position.x <= 0 }
    }

    fun canMoveRight(rock: Rock): Boolean {
        return rock.positions.none { position -> position.x >= 7 }
    }

    fun canMoveDown(rock: Rock, row: Int): Boolean {
        if (row == 0) return false
        return rock.getRockBottomEdge().intersect(rocks.getRowAt(row)).isNotEmpty()
    }
}

class DaySeventeen(private val input: String) {
    private val RIGHT = '>'
    private val LEFT = '<'

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
        var round = 0
        var jet = 0
        val highestRock = chamber.rocks.minY()

        while (round < rounds) {
            val shapeIdx = round % Shape.values().size
            val rockTemplate = rockSequence[shapeIdx]
            val heightIdx = highestRock - 3 // rocks start three positions above the highest rock
            val startingPosition = Position(2, heightIdx)
            var fallingRock = rockTemplate.moveTo(startingPosition)

            var canMove = true

            while (canMove) {
                val jetIdx = jet % jets.size
                val motion = jets[jetIdx]

                fallingRock = when (motion) {
                    LEFT -> {
                        if(chamber.canMoveLeft(fallingRock)) {
                            fallingRock.moveLeft()
                        } else {
                            fallingRock
                        }
                    }
                    RIGHT -> {
                        if (chamber.canMoveRight(fallingRock)) {
                            fallingRock.moveRight()
                        } else {
                            fallingRock
                        }
                    }
                    else -> throw UnsupportedOperationException("Motion not supported")
                }

                if (chamber.canMoveDown(fallingRock, heightIdx + 1)) {
                    fallingRock = fallingRock.moveDown()
                } else {
                    chamber.rocks.addTo(fallingRock.positions.toList())
                    canMove = false
                }
                println(chamber.rocks.print2D())
                jet++
            }
            round++
        }
        return highestRock
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day17_2022.txt")
    val solver = DaySeventeen(input)
    println(solver.partOne(rounds = 2022))
}
