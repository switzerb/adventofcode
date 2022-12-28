package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Position
import aoc.lib.Resources.fileAsString
import java.lang.StringBuilder

class Board {
    var grid: MutableMap<Position, Char> = mutableMapOf()

    fun getStart(): Position {
        val lowestX = grid.keys.filter { p -> p.y == 0 }.map { it.x }.minOrNull() ?: 0
        return Position(lowestX, 0)
    }

    fun canMove(position: Position) = grid.contains(position) && grid[position] == '.'

    fun move(current: Player, units: Int): Player {
        var position = current.current
        var steps = 0
        when (current.facing) {
            Direction.NORTH -> {
                while (steps < units) {
                    val next = Position(position.x, position.y - 1)
                    if (canMove(next)) {
                        position = next
                    }
                    steps++
                }
                return Player(position, current.facing)
            }
            Direction.SOUTH -> {
                while (steps < units) {
                    val next = Position(position.x, position.y + 1)
                    if (canMove(next)) {
                        position = next
                    }
                    steps++
                }
                return Player(position, current.facing)
            }
            Direction.EAST -> {
                while (steps < units) {
                    val next = Position(position.x + 1, position.y)
                    if (canMove(next)) {
                        position = next
                    }
                    steps++
                }
                return Player(position, current.facing)
            }
            Direction.WEST -> {
                while (steps < units) {
                    val next = Position(position.x - 1, position.y)
                    if (canMove(next)) {
                        position = next
                    }
                    steps++
                }
                return Player(position, current.facing)
            }
        }
    }

    fun turn(current: Player, dir: String): Player = Player(current.current, facing = current.facing.turn(to = dir))

    fun printBoard(player: Player): String {
        val positions = grid.keys
        val maxY = positions.map { it.y }.maxOrNull() ?: 0
        val maxX = positions.map { it.x }.maxOrNull() ?: 0

        val string = StringBuilder()
        string.append("\n")
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                val p = Position(x, y)
                if (player.current == p) {
                    when (player.facing) {
                        Direction.EAST -> string.append(">")
                        Direction.WEST -> string.append("<")
                        Direction.NORTH -> string.append("^")
                        Direction.SOUTH -> string.append("v")
                    }
                } else {
                    when {
                        grid.contains(p) -> string.append(grid[p])
                        else -> string.append(' ')
                    }
                }
            }
            string.append("\n")
        }
        return string.toString()
    }
}

data class Player(val current: Position, val facing: Direction)

class DayTwentyTwo(private val input: String) {
    val board = Board()
    var directions = ""

    init {
        val (map, directions) = input.split("\n\n")
        val rows = map.split("\n")
        val grid = mutableMapOf<Position, Char>()
        var y = 0
        for (row in rows) {
            var x = 0
            for (item in row) {
                if (item.isWhitespace()) {
                    x++
                } else {
                    grid[Position(x, y)] = item
                    x++
                }
            }
            y++
        }
        board.grid = grid
        this.directions = directions
    }

    val instructions: List<String> = directions
        .let { """\d+|[LR]""".toRegex().findAll(it) }
        .map { it.value }
        .toList()

    fun step(instruction: String, player: Player): Player {
        return when (instruction) {
            "R" -> board.turn(player, "R")
            "L" -> board.turn(player, "L")
            else -> board.move(
                current = player,
                units = instruction.toInt()
            )
        }
    }

    fun partOne(): Int {
        // if grid doesn't contain, it means we are off the map and we need to wrap
        var player = Player(board.getStart(), Direction.EAST)
        for (step in instructions) {
            player = step(step, player)
            println(board.printBoard(player))
            println(step)
        }
        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day22_2022.txt")
    val solver = DayTwentyTwo(input)
    println(solver.partOne())
}
