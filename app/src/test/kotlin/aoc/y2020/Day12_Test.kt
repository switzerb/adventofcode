package aoc.y2020

import aoc.lib.Dir
import aoc.lib.Position
import org.junit.Test
import kotlin.test.assertEquals

class DayTwelve2020Test {

    val input = listOf("F10","N3","F7","R90","F11")
    val solver = DayTwelve2020(input)

    @Test
    fun ex1() { assertEquals(25, solver.partOne()) }

    @Test
    fun ex2() { assertEquals(286, solver.partTwo()) }

    @Test
    fun waypointRotation() {
        val waypoint = Waypoint(Position(2,3))
        val ninety = Waypoint(Position(3,-2))
        val oneEighty = Waypoint(Position(-2,-3))
        assertEquals(ninety, waypoint.clockwise(90))
        assertEquals(oneEighty, waypoint.clockwise(180))
    }
    @Test
    fun waypointRotation2() {
        val waypoint = Waypoint(Position(-5,-1))
        val ninety = Waypoint(Position(-1,5))
        val oneEighty = Waypoint(Position(5,1))
        assertEquals(ninety, waypoint.clockwise(90))
        assertEquals(oneEighty, waypoint.clockwise(180))
    }

    @Test
    fun moveShipToWaypoint() {
        val ship = Ship(Position.ORIGIN, Dir.EAST, Waypoint())
        assertEquals(Position(100,10),ship.moveShipToWaypoint(10))
    }

    @Test
    fun moveShipToWaypoint2() {
        val ship = Ship(Position(170,38),Dir.EAST, Waypoint(Position(4,-10)))
        assertEquals(Position(214,-72), ship.moveShipToWaypoint(11))
    }

}
