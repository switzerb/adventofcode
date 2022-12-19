package aoc.lib

import kotlin.math.abs

class Vector {
    private val coords: MutableList<Int> = mutableListOf()

    companion object {
        val ORIGIN2D = Vector(0, 0)
        val ORIGIN3D = Vector(0, 0, 0)

        fun adjacentDeltas(dim: Int = 3): List<Vector> {
            val origin = Vector(emptyList())
            var deltas = mutableListOf(origin)

            repeat(dim) {
                deltas = deltas.flatMap { d ->
                    (-1..1).map {
                        val l = mutableListOf<Int>()
                        l.addAll(d.coords)
                        l.add(it)
                        Vector(l)
                    }
                }.toMutableList()
            }
            deltas.removeAt(deltas.size / 2)
            return deltas
        }

        fun from(input: String): Vector =
            input.split(",").let { (x, y, z) -> Vector(x.toInt(), y.toInt(), z.toInt()) }
    }

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

    operator fun get(dimension: Int): Int = coords[dimension]
    fun x(): Int = coords[0]
    fun y(): Int = coords[1]
    fun z(): Int = coords[2]

    fun getManhattanDistance(other: Vector) = abs(x() - other.x()) + abs(y() - other.y())

    fun moveBy(dir: Direction, units: Int): Vector = when (dir) {
        Direction.EAST -> Vector(x() + units, y())
        Direction.WEST -> Vector(x() - units, y())
        Direction.NORTH -> Vector(x(), y() + units)
        Direction.SOUTH -> Vector(x(), y() - units)
    }

    operator fun plus(other: Vector): Vector {
        return Vector(this.coords.zip(other.coords).map { it.first + it.second })
    }

//    fun neighbors(): List<Vector> = adjacentDeltas(coords.size).map { it + this }

    fun neighbors(): Set<Vector> =
        setOf(
            Vector(x = x() - 1, y = y(), z = z()),
            Vector(x = x() + 1, y = y(), z = z()),
            Vector(x = x(), y = y() - 1, z = z()),
            Vector(x = x(), y = y() + 1, z = z()),
            Vector(x = x(), y = y(), z = z() - 1),
            Vector(x = x(), y = y(), z = z() + 1)
        )

    override fun equals(other: Any?): Boolean {
        if (other !is Vector) {
            return false
        }
        return this.coords == other.coords
    }

    override fun hashCode(): Int {
        return this.coords.hashCode()
    }

    // TODO: Implement to string
    override fun toString(): String {
        return this.coords.toString()
    }
}
