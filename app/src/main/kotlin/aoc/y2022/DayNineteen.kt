package aoc.y2022

import aoc.lib.Resources.fileAsString

sealed class Robot {
    abstract fun build(): Int
    fun produce(): Int
}

data class Clay : Robot() {
    override fun build(): Int {
        TODO("Not yet implemented")
    }
}

data class Blueprint(
    val id: Int,
    val oreCost: Int,
    val clayCost: Int,
    val obsidianCost: Int,
    val geodeCost: Int,
) {
    fun buildRobot(type:)

}

class DayNineteen(private val input: String) {

    // ore -> clay -> obsidian -> geode
    var ore: Int = 0
    var oreRobotCount = 1

    // if we have enough ore, build a clay robot and increment

    // if we have enough ore and clay, build an obsidian robot

    // if we have enough ore and obsidian, build a geode robot

    // Determine the quality level of each blueprint by multiplying that blueprint's ID number
    fun qualityLevel(id: Int, geodes: Int) = id * geodes

    // represents action in one minute
    fun tick() {
        ore += 1 * oreRobotCount

        // total clay
        // increment ore from ore robot
        // if there is enough ore, make a clay robot
    }

    fun partOne(timer: Int): Int {
        // for each blueprint
        repeat(timer) {
            tick()
        }

//        Blueprint 1:
//        Each ore robot costs 4 ore.
//        Each clay robot costs 2 ore.
//        Each obsidian robot costs 3 ore and 14 clay.
//        Each geode robot costs 2 ore and 7 obsidian.
        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day19_2022.txt")
    val solver = DayNineteen(input)
    println(solver.partOne())
}
