package aoc.y2022

import aoc.lib.Grid2D
import aoc.lib.Position
import aoc.lib.Resources.fileAsString
import kotlin.math.abs

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
    val coords: List<Position>,
    val tall: Int
) {
    fun move(p: Position): Rock {
        val newPositions = buildList {
            for (pos in coords) {
                add(pos.plus(p))
            }
        }
        return copy(coords = newPositions)
    }

    fun right(): Rock = this.move(Position(1, 0))
    fun left(): Rock = this.move(Position(-1, 0))
    fun down(): Rock = this.move(Position(0, 1))
}

class DaySeventeen(private val input: String) {
    val atRest: Grid2D = Grid2D()

    private val rockSequence = listOf(
        Rock(
            shape = Shape.HLINE,
            coords = listOf(Position(0, 0), Position(1, 0), Position(2, 0), Position(3, 0)),
            tall = 1
        ),
        Rock(
            shape = Shape.DIAMOND,
            coords = listOf(Position(1, 0), Position(0, -1), Position(1, -1), Position(2, -1), Position(1, -2)),
            tall = 3
        ),
        Rock(
            shape = Shape.DEL,
            coords = listOf(Position(0, 0), Position(1, 0), Position(2, 0), Position(2, -1), Position(2, -2)),
            tall = 3
        ),
        Rock(
            shape = Shape.VLINE,
            coords = listOf(Position(0, 0), Position(0, -1), Position(0, -2), Position(0, -3)),
            tall = 4
        ),
        Rock(
            shape = Shape.SQUARE,
            coords = listOf(Position(0, 0), Position(1, 0), Position(0, -1), Position(1, -1)),
            tall = 2
        )
    )
    val jets = input.toCharArray()

    fun couldMove(rock: Rock) =
        rock.coords.all {
            it.x in 0..6 &&
                it.y <= 0 &&
                !atRest.grid.contains(it)
        }

    fun draw(rock: Rock? = null) =
        buildString {
            for (y in atRest.minY() - 5..0) {
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

    fun partOne(rounds: Int): Int {
        var rockIdx = 0
        var jetIdx = 0
        var height = 0

        repeat(rounds) {
            val nextRock = rockSequence[rockIdx++ % Shape.values().size]
            var rock = nextRock.move(Position(2, height - 3))
            while (true) {
                val motion = jets[jetIdx++ % jets.size]

                val pushed = when (motion) {
                    '<' -> rock.left()
                    '>' -> rock.right()
                    else -> throw UnsupportedOperationException("Motion not supported")
                }
                if (couldMove(pushed)) rock = pushed
//                println("Moves $motion...")
//                println(draw(rock))
                val dropped = rock.down()
                if (couldMove(dropped)) {
                    rock = dropped
//                    println("Drops...")
//                    println(draw(rock))
                } else {
                    atRest.addTo(rock.coords.toList())
                    height = if (height == 0) -rock.tall else atRest.minY() - 1
//                    println("At rest...")
//                    println(draw(rock))
                    break
                }
            }
        }
        return abs(height)
    }

    fun partTwo(): Long {
        return 0L
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day17_2022.txt")
    val solver = DaySeventeen(input)
    println(solver.partOne(rounds = 2022)) // 3147
}
