package aoc.y2022

import aoc.lib.Direction
import aoc.lib.Position
import aoc.lib.Resources.fileAsString

typealias Blizzard = Direction

data class Valley(
    val start: Position,
    val end: Position,
    val width: Int,
    val height: Int,
    val blizzards: Map<Position, MutableList<Blizzard>>
) {
    fun draw(expedition: Position? = start): String {
        val str = StringBuilder()
        for (y in 0..height) {
            for (x in 0..width) {
                val current = Position(x, y)
                if (current == expedition) {
                    str.append("E")
                } else if (current == start || current == end) {
                    str.append(".")
                } else if (x == 0 || x == width || y == 0 || y == height) {
                    str.append("#")
                } else if (blizzards.containsKey(current)) {
                    if (blizzards[current]!!.size > 1) {
                        str.append(blizzards[current]!!.size.toString())
                    } else {
                        when (blizzards[current]!!.first()) {
                            Blizzard.NORTH -> str.append("^")
                            Blizzard.SOUTH -> str.append("v")
                            Blizzard.WEST -> str.append("<")
                            Blizzard.EAST -> str.append(">")
                        }
                    }
                } else {
                    str.append(".")
                }
            }
            str.append("\n")
        }
        return str.toString()
    }

    override fun toString(): String = draw()
}

class DayTwentyFour(private val input: String) {
    val rows = input.split("\n")
    val width = rows.first().length - 1
    val height = rows.size - 1
    val startX = getStartorEnd(row = rows.first()) ?: throw IllegalArgumentException("No start found.")
    val endX = getStartorEnd(row = rows.last()) ?: throw IllegalArgumentException("No start found.")
    val start = Position(startX, 0)
    val end = Position(endX, height)
    val blizzards = buildMap {
        rows.mapIndexed { y, row ->
            row.mapIndexed { x, char ->
                val dir = when (char) {
                    '^' -> Blizzard.NORTH
                    'v' -> Blizzard.SOUTH
                    '<' -> Blizzard.WEST
                    '>' -> Blizzard.EAST
                    else -> null
                }
                if (dir != null) {
                    put(Position(x, y), mutableListOf(dir))
                }
            }
        }
    }

    data class State(val expedition: Position, val valley: Valley, val steps: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is State) return false

            if (expedition != other.expedition) return false
            if (!valley.blizzards.equals(other.valley.blizzards)) return false
            return true
        }

        override fun hashCode(): Int {
            var result = expedition.hashCode()
            result = 31 * result + valley.hashCode()
            return result
        }
    }

    private fun getStartorEnd(row: String): Int? {
        val idx = row.toCharArray().indexOf('.')
        return if (idx == -1) null else idx
    }

    private fun getNextPosition(current: Position, dir: Blizzard): Position {
        return when (dir) {
            Direction.SOUTH -> if (current.y == height - 1) Position(current.x, 1) else current.south()
            Direction.NORTH -> if (current.y == 1) Position(current.x, height - 1) else current.north()
            Direction.WEST -> if (current.x == 1) Position(width - 1, current.y) else current.west()
            Direction.EAST -> if (current.x == width - 1) Position(1, current.y) else current.east()
        }
    }

    fun tick(current: Valley): Valley {
        val newState = mutableMapOf<Position, MutableList<Blizzard>>()
        current.blizzards.forEach { (position, items) ->
            for (blizzard in items) {
                val next = getNextPosition(position, blizzard)
                val value = newState.getOrPut(next) { mutableListOf() }
                value.add(blizzard)
                newState[next] = value
            }
        }
        return current.copy(blizzards = newState.toMap())
    }

    fun initialValley(): Valley = Valley(
        start = start,
        end = end,
        width = width,
        height = height,
        blizzards = blizzards
    )

    fun isEdge(p: Position): Boolean {
        if (p == start) return false
        if (p == end) return false
        return p.x <= 0 || p.y <= 0 || p.x >= width || p.y >= height
    }

    fun partOne(): Int {
        val visited = mutableSetOf<State>()
        val queue = mutableListOf<State>()

        val state = State(expedition = start, valley = initialValley(), steps = 0)
        queue.add(state)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            if (current !in visited) {
                visited.add(current)
                val options = mutableListOf<Position>()
                options.add(current.expedition)
                options.addAll(current.expedition.nextTo())
                val moves = options
                    .filterNot { isEdge(it) }
                    .filterNot { it in current.valley.blizzards.keys }

                if (moves.any { it == end }) return current.steps
                println(current.steps)
                if (current.steps > 500) {
                    break
                }
                moves.forEach {
                    queue.add(
                        State(
                            expedition = it,
                            valley = tick(current.valley),
                            steps = current.steps + 1
                        )
                    )
                }
            }
        }
        throw IllegalStateException("No valid path found.")
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day24_2022.txt")
    val solver = DayTwentyFour(input)
    println(solver.partOne())
}
