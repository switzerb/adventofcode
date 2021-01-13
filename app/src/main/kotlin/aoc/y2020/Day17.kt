package aoc.y2020

import aoc.lib.Vector

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

//    Each cube only ever considers its neighbors:
//    any of the 26 other cubes where any of their coordinates
//    differ by at most 1. For example, given the
//    cube at x=1,y=2,z=3, its neighbors include
//    the cube at x=2,y=2,z=2, the cube at x=0,y=2,z=3, and so on.
    fun getNeighbors() : List<Vector> {
    /**
     * loop over deltas to get all the neighbors
     * 3 cubed minus 1 (26)
     * activeThing +
     * vector(1,0,0)
     * vector(0,0,0)
     * vector(1,0,0)
     * vector(-1,0,0)
     * vector(0,1,0)
     * vector(0,-1,0)
     * vector(0,0,1)
     * vector(0,0,-1)
     */
        return emptyList()
    }

    /**
     *  During a cycle, all cubes simultaneously change their state according to the following rules:
     *  - If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
     *  - If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
     */
    fun cycle(): Unit {
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
