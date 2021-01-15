package aoc.y2020

import aoc.lib.Vector

val deltas = listOf(
    Vector( 1, 1,1),
    Vector( 0, 1, 1),
    Vector(-1, 1, 1),
    Vector( 1, 0, 1),
    Vector( 0, 0, 1),
    Vector(-1, 0, 1),
    Vector( 1,-1, 1),
    Vector( 0,-1, 1),
    Vector(-1,-1, 1),
    Vector( 1, 1, 0),
    Vector( 0, 1, 0),
    Vector(-1, 1, 0),
    Vector( 1, 0, 0),
    Vector(-1, 0, 0),
    Vector( 1,-1, 0),
    Vector( 0,-1, 0),
    Vector(-1,-1, 0),
    Vector( 1, 1,-1),
    Vector( 0, 1,-1),
    Vector(-1, 1,-1),
    Vector( 1, 0,-1),
    Vector( 0, 0,-1),
    Vector(-1, 0,-1),
    Vector( 1,-1,-1),
    Vector( 0,-1,-1),
    Vector(-1,-1,-1)
)

data class PocketDimension(val cubes: Set<Vector>) {

    //either search or add richness to data
    // get bounds on next tick

    companion object {
        //set of active cubes
        fun parse(input: String) : PocketDimension {
            val init =  setOf(
                Vector(1,0,0),
                Vector(2,0,0),
                Vector(0,2,0),
                Vector(1,2,0),
                Vector(2,2,0)
            )
            return PocketDimension(init)
        }
    }

    override fun toString(): String {
        return super.toString()
    }
}

class Day172020(val input: String) {

    fun getNeighbors(cube: Vector) : List<Vector> {
        return deltas.map { it + cube }
    }

    /**
     *  During a cycle, all cubes simultaneously change their state according to the following rules:
     *  - If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
     *  - If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
     */
    fun cycle(): Unit {
        val next = mutableSetOf<Vector>()
        val pd = PocketDimension.parse("initial")
        pd.cubes.map { cube ->
            // for each active cube in my pocket dimension
            println(cube)
            // get all the adjacent cubes and find the count of active
            val neighbors = getNeighbors(cube)
                .filter { pd.cubes.contains(it)}
                .size
            if (neighbors == 2 || neighbors == 3) {
                next.add(cube)
            }
// If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.

        }
        // since it's simultaneous, then we can't mutate in place - write a new copy of the map?
    }

    fun partOne(): Int {
        val start = PocketDimension.parse("noop")
        println(start)
        // feed it into one cycle
        // get back the next map to feed into cycle
        // return how many are active
        return 0
    }

    fun partTwo(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    val solver = Day172020("noop")
    solver.partOne()
}
