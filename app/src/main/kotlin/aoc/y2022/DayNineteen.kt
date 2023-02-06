package aoc.y2022

import aoc.lib.Resources.fileAsString
import java.util.*

typealias Cost = Map<Resource, Int>

enum class Resource {
    ORE,
    CLAY,
    OBSIDIAN,
    GEODE,
}

const val TIME_ALLOWED = 24

class DayNineteen(private val input: String) {

    data class State(
        val currentTime: Int,
        var materials: Map<Resource, Int> = mapOf(
            Resource.ORE to 0,
            Resource.CLAY to 0,
            Resource.OBSIDIAN to 0,
            Resource.GEODE to 0
        ),
        val production: Map<Resource, Int> = mapOf(
            Resource.ORE to 1,
            Resource.CLAY to 0,
            Resource.OBSIDIAN to 0,
            Resource.GEODE to 0
        )
    ) : Comparable<State> {

        fun canPurchase(cost: Cost): Boolean {
            for (resource in cost.keys) {
                if (!materials.containsKey(resource)) return false
                if (materials[resource]!! < cost[resource]!!) return false
            }
            return true
        }

        fun buildRobot(resource: Resource): State {
            val newState = this.copy()
             production.plus(Pair(resource, production.getOrDefault(resource, 0) + 1))
            return newState
        }


        fun collect(): Map<Resource, Int> {
            val next = mutableMapOf<Resource, Int>()
            for (resource in production.keys) {
                next[resource] = materials.getOrDefault(resource, 0) + production.getOrDefault(resource, 0)
            }
            return next
        }

        fun getGeodeCount(): Int = materials.getOrDefault(Resource.GEODE, 0)
        override fun compareTo(other: State): Int = other.getGeodeCount().compareTo(getGeodeCount())
    }

    data class Blueprint(
        val id: Int,
        val ore: Cost,
        val clay: Cost,
        val obsidian: Cost,
        val geode: Cost
    ) {
        //  to construct any type of robot, although it consumes
        //  the necessary resources available when construction begins.
        fun tick(state: State): List<State> {
            val nexts = mutableListOf<State>()

            // 1. do nothing
            nexts.add(state.copy(currentTime = state.currentTime + 1))

            // 2. build an ore robot
            if (state.canPurchase(this.ore)) {
                nexts.add(state.buildRobot(Resource.ORE))
            }

            // 3. clay
            if (state.canPurchase(this.clay)) {
                nexts.add(state.copy(production = state.buildRobot(Resource.CLAY)))
            }

            // 4. obsidian
            if (state.canPurchase(this.obsidian)) {
                nexts.add(state.copy(production = state.buildRobot(Resource.OBSIDIAN)))
            }

            // 5. geode
            if (state.canPurchase(this.geode)) {
                nexts.add(state.copy(production = state.buildRobot(Resource.GEODE)))
            }

            // collect from existing robots, we want to collect for every option
            for (next in nexts) {
                next.materials = state.collect()
            }

            return nexts.filter { it.currentTime <= TIME_ALLOWED }
        }
    }

    // Determine the quality level of each blueprint by multiplying that blueprint's ID number
    fun qualityLevel(id: Int, geodes: Int) = id * geodes

    val blueprints = listOf(
        Blueprint(
            id = 1,
            ore = mapOf(Resource.ORE to 4),
            clay = mapOf(Resource.ORE to 2),
            obsidian = mapOf(Resource.ORE to 3, Resource.CLAY to 14),
            geode = mapOf(Resource.ORE to 2, Resource.OBSIDIAN to 7)
        ),
        Blueprint(
            id = 2,
            ore = mapOf(Resource.ORE to 2),
            clay = mapOf(Resource.ORE to 3),
            obsidian = mapOf(Resource.ORE to 3, Resource.CLAY to 8),
            geode = mapOf(Resource.ORE to 3, Resource.OBSIDIAN to 12)
        )
    )

    private fun geodesFinder(blueprint: Blueprint, timer: Int): Int {
        var maxGeodes = 0
        val queue = PriorityQueue<State>().apply { add(State()) }

        while (queue.isNotEmpty()) {
            val state = queue.poll()
            queue.addAll(state.tick(blueprint, timeRemaining = timer))
            maxGeodes = maxOf(maxGeodes, state.getGeodeCount())
        }

        return maxGeodes
    }

    fun partOne(timer: Int): Int {
        val blueprint = blueprints[0]

        geodesFinder(blueprint, timer)

        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day19_2022.txt")
    val solver = DayNineteen(input)
    println(solver.partOne(1))
}
