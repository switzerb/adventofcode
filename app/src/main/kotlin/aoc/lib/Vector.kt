package aoc.lib

import kotlin.math.abs

class Vector {
    private val coords: MutableList<Int> = mutableListOf()

    constructor(coords: List<Int>) {
        this.coords.addAll(coords)
    }

    constructor(x: Int, y: Int) {
        this.coords.add(x)
        this.coords.add(y)
    }

    constructor(x: Int, y: Int, z: Int) {
        this.coords.add(x)
        this.coords.add(y)
        this.coords.add(z)
    }

    fun get(dimension: Int): Int = coords[dimension]
    fun x(): Int = get(0)
    fun y(): Int = get(1)
    fun z(): Int = get(2)

    fun getManhattanDistance(other: Vector) =  abs(x() - other.x()) + abs(y() - other.y())

    fun moveBy(dir: Dir, units: Int): Vector = when (dir) {
        Dir.EAST -> Vector(x() + units, y())
        Dir.WEST -> Vector(x() - units, y())
        Dir.NORTH -> Vector(x(), y() + units)
        Dir.SOUTH -> Vector(x(), y() - units)
    }

    companion object {
        val ORIGIN2D = Vector(0, 0)
        val ORIGIN3D = Vector(0, 0, 0)
    }

}
