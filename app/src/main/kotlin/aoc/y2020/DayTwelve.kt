package aoc.y2020

import aoc.lib.Dir
import aoc.lib.Position
import aoc.lib.Resources.fileAsList

data class Action(val type: Char, val units: Int)

data class Waypoint(val pos : Position = Position(10, 1)) {

    fun clockwise(deg: Int): Waypoint = Waypoint((0 until deg/90).fold(pos) { p, _ -> Position(p.y, -p.x)})

    fun move(dir: Dir, units: Int): Waypoint = Waypoint(pos.moveBy(dir, units))

}

data class Ship(val pos: Position, val facing: Dir, val waypoint: Waypoint = Waypoint()) {

    private fun clockwise(deg: Int): Ship = Ship(pos, (0 until deg/90).fold(facing) { f, _ -> f.clockwise() })

    private fun forward(units: Int): Ship = move(facing, units)

    private fun move(dir: Dir, units: Int): Ship = Ship(pos.moveBy(dir, units), facing)

    fun run(action: Char, units: Int): Ship {
        return when (action) {
            'N' -> move(Dir.NORTH, units)
            'S' -> move(Dir.SOUTH, units)
            'E' -> move(Dir.EAST, units)
            'W' -> move(Dir.WEST, units)
            'L' -> clockwise(360 - units)
            'R' -> clockwise(units)
            'F' -> forward(units)
            else -> throw IllegalArgumentException("The action $action is not understood.")
        }
    }

    fun moveShipToWaypoint(units: Int): Position =
        Position(pos.x + waypoint.pos.x * units, pos.y + waypoint.pos.y * units)

    fun runWithWaypoint(action: Char, units: Int) : Ship {
        return when (action) {
            'N' -> Ship(pos, facing, waypoint.move(Dir.NORTH, units))
            'S' -> Ship(pos, facing, waypoint.move(Dir.SOUTH, units))
            'E' -> Ship(pos, facing, waypoint.move(Dir.EAST, units))
            'W' -> Ship(pos, facing, waypoint.move(Dir.WEST, units))
            'L' -> Ship(pos, facing, waypoint.clockwise( 360 - units))
            'R' -> Ship(pos, facing, waypoint.clockwise(units))
            'F' -> Ship(moveShipToWaypoint(units), facing, waypoint)
            else -> throw IllegalArgumentException("The action $action is not understood.")
        }
    }
}

class DayTwelve2020(input: List<String>) {

    private val actions: List<Action> = input.map { Action(it.first(), it.drop(1).toInt()) }

    fun partOne(): Int {
        val start = Ship(Position.ORIGIN, Dir.EAST)
        val destination = actions.fold(start) { current, action ->
                current.run(action.type, action.units)
            }
        return Position.ORIGIN.getManhattanDistance(destination.pos)
    }

    fun partTwo(): Int {
        val waypoint = Waypoint(Position(10, 1))
        val start = Ship(Position.ORIGIN, Dir.EAST, waypoint)
        val destination = actions.fold(start) { current, action ->
            current.runWithWaypoint(action.type, action.units)
        }
        return Position.ORIGIN.getManhattanDistance(destination.pos)
    }
}

fun main(args: Array<String>) {
    val solver = DayTwelve2020(fileAsList("day12_2020.txt"))
    println(solver.partOne()) //2228
    println(solver.partTwo()) //42908
}
