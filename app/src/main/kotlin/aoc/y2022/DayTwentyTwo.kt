package aoc.y2022

import aoc.lib.Position
import aoc.lib.Resources.fileAsString
import java.lang.StringBuilder

class Board {
    var grid: MutableMap<Position, Char> = mutableMapOf()

    override fun toString(): String {
        val positions = grid.keys
        val maxY = positions.map { it.y }.maxOrNull() ?: 0
        val maxX = positions.map { it.x }.maxOrNull() ?: 0

        val string = StringBuilder()
        string.append("\n")
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                val p = Position(x, y)
                if (grid.contains(p)) {
                    string.append(grid[p])
                } else {
                    string.append(' ')
                }
            }
            string.append("\n")
        }
        return string.toString()
    }
}

class DayTwentyTwo(private val input: String) {

    fun parse(): Board {
        val (map, directions) = input.split("\n\n")
        val rows = map.split("\n")
        val board = Board()
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
        println(board)
        return board
    }

    fun partOne(): Int {
        // if grid doesn't contain, it means we are off the map and we need to wrap
        val board = parse()
        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day22_2022.txt")
    val solver = DayTwentyTwo(input)
    println(solver.partOne())
}
