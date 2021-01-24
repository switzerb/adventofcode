package aoc.y2020

import aoc.lib.Vector

data class PocketDimension(val cubes: Set<Vector>) {

    // TODO: get bounds on next tick as data
    private val minX = 0
    private val maxX = 2
    private val minY = 0
    private val maxY = 2

    companion object {
        // set of active cubes
        fun parse(input: String) : PocketDimension {
            val init =  setOf(
                Vector(1,0,0),
                Vector(2,1,0),
                Vector(0,2,0),
                Vector(1,2,0),
                Vector(2,2,0)
            )
            return PocketDimension(init)
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        val layers = this.cubes.groupBy { it.z() }
        layers.keys.reversed().map { z ->
            sb.append("z=$z\n")
            (minY..maxY).map { y ->
                (minX..maxX).map { x ->
                    if (cubes.contains(Vector(x,y,z))) sb.append("#") else sb.append(".")
                }
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    fun activeNeighbors(cube: Vector) : Int {
        // for each active cube in my pocket dimension
        // get all the adjacent cubes and find the count of active
        return cube
            .neighbors()
            .filter { this.cubes.contains(it) }
            .size
    }

    private fun isActive(cube: Vector) : Boolean = cubes.contains(cube)

    /**
     *  During a cycle, all cubes simultaneously change their state according to the following rules:
     *  - If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
     *  - If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
     */
    fun cycle(): PocketDimension {
        val next = mutableSetOf<Vector>()
        // for each active
        // for each neighbor of active
        cubes.map { cube ->
            val active = isActive(cube)
            if(activeNeighbors(cube) == 2 || activeNeighbors(cube) == 3) next.add(cube)
            cube.neighbors().forEach { neighbor ->
                if(activeNeighbors(neighbor) == 3) next.add(neighbor)
            }
        }
        return PocketDimension(next)
    }
    fun count() : Int = cubes.size
}

class Day172020(val input: String) {

    fun partOne(rounds: Int = 6): Int {
        var pd = PocketDimension.parse("noop")
        repeat(rounds) {
            pd = pd.cycle()
        }
//        println(pd)
        return pd.count()
    }

    fun partTwo(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    val solver = Day172020("noop")
    solver.partOne()
}
