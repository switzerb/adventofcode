package aoc.y2020

import aoc.lib.Resources.fileAsList
import aoc.lib.Vector

data class PocketDimension(val cubes: Set<Vector>) {

    companion object {
        // set of active cubes
        fun parse(input: List<String>) : PocketDimension {
            val init = mutableSetOf<Vector>()
            input.mapIndexed { y, row ->
                row.mapIndexed { x, cube ->
                    if(cube == '#') init.add(Vector(x,y,0))
                 }
            }
            return PocketDimension(init)
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        val layers = this.cubes.groupBy { it.z() }
        layers.keys.reversed().map { z ->
            sb.append("z=$z\n")
            (0..5).map { y ->
                (0..5).map { x ->
                    if (cubes.contains(Vector(x,y,z))) sb.append("#") else sb.append(".")
                }
                sb.append("\n")
            }
        }
        return sb.toString()
    }

    private fun activeNeighbors(cube: Vector) : Int {
        return cube
            .neighbors()
            .filter { this.cubes.contains(it) }
            .size
    }

    /**
     *  During a cycle, all cubes simultaneously change their state according to the following rules:
     *  - If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
     *  - If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
     */
    fun cycle(): PocketDimension {
        val next = mutableSetOf<Vector>()
        cubes.map { cube ->
            if(activeNeighbors(cube) == 2 || activeNeighbors(cube) == 3) next.add(cube)
            cube.neighbors().forEach { neighbor ->
                if(activeNeighbors(neighbor) == 3) next.add(neighbor)
            }
        }
        return PocketDimension(next)
    }

    fun count() : Int = cubes.size
}

class Day172020(val input: List<String>) {

    fun partOne(rounds: Int = 6): Int {
        var pd = PocketDimension.parse(input)
        repeat(rounds) {
            pd = pd.cycle()
        }
        return pd.count()
    }

    fun partTwo(rounds: Int = 6): Int {
        var pd = PocketDimension.parse(input)
        repeat(rounds) {
            pd = pd.cycle()
        }
        return pd.count()
    }
}

fun main(args: Array<String>) {
    val solver = Day172020(fileAsList("day17_2020.txt"))
    println(solver.partOne()) // 386
    println(solver.partTwo()) // 386
}
