package aoc.y2022

import aoc.lib.Position
import aoc.lib.Resources.fileAsString

class Sensor(
    val location: Position,
    val closestBeacon: Position
) {
    val distance = location.getManhattanDistance(closestBeacon)

    fun positionsInRange(rowOfInterest: Int): List<Position> {
        val positions = mutableListOf<Position>()
        val yRange = location.y - distance..location.y + distance
        var maxX = location.x + distance
        var minX = location.x - distance
        var count = distance
        if (rowOfInterest in yRange) {
            for (y in location.y + 1..location.y + distance + 1) {
                positions.addAll(Position(minX, y).lineTo(Position(maxX, y)))
                minX++
                maxX--
            }
            for (y in location.y downTo location.y - distance) {
                positions.addAll(Position(location.x - count, y).lineTo(Position(location.x + count, y)))
                count--
            }
            positions.filter { position -> position.y == rowOfInterest }
        }
        return positions.toList()
    }
}

class DayFifteen(private val input: String) {

    val sensors = listOf<Sensor>(
        Sensor(location = Position(2, 18), closestBeacon = Position(-2, 15)),
        Sensor(location = Position(9, 16), closestBeacon = Position(10, 16)),
        Sensor(location = Position(13, 2), closestBeacon = Position(15, 3)),
        Sensor(location = Position(12, 14), closestBeacon = Position(10, 16)),
        Sensor(location = Position(10, 20), closestBeacon = Position(10, 16)),
        Sensor(location = Position(14, 17), closestBeacon = Position(10, 16)),
        Sensor(location = Position(8, 7), closestBeacon = Position(2, 10)),
        Sensor(location = Position(2, 0), closestBeacon = Position(2, 10)),
        Sensor(location = Position(0, 11), closestBeacon = Position(2, 10)),
        Sensor(location = Position(20, 14), closestBeacon = Position(25, 17)),
        Sensor(location = Position(17, 20), closestBeacon = Position(21, 22)),
        Sensor(location = Position(16, 7), closestBeacon = Position(15, 3)),
        Sensor(location = Position(14, 3), closestBeacon = Position(15, 3)),
        Sensor(location = Position(20, 1), closestBeacon = Position(15, 3))
    )

//    val sensors = listOf(
//        Sensor(location = Position(545406, 2945484), closestBeacon = Position(772918, 2626448)),
//        Sensor(location = Position(80179, 3385522), closestBeacon = Position(772918, 2626448)),
//        Sensor(location = Position(2381966, 3154542), closestBeacon = Position(2475123, 3089709)),
//        Sensor(location = Position(2607868, 1728571), closestBeacon = Position(2715626, 2000000)),
//        Sensor(location = Position(746476, 2796469), closestBeacon = Position(772918, 2626448)),
//        Sensor(location = Position(911114, 2487289), closestBeacon = Position(772918, 2626448)),
//        Sensor(location = Position(2806673, 3051666), closestBeacon = Position(2475123, 3089709)),
//        Sensor(location = Position(1335361, 3887240), closestBeacon = Position(2505629, 4282497)),
//        Sensor(location = Position(2432913, 3069935), closestBeacon = Position(2475123, 3089709)),
//        Sensor(location = Position(1333433, 35725), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(2289207, 1556729), closestBeacon = Position(2715626, 2000000)),
//        Sensor(location = Position(2455525, 3113066), closestBeacon = Position(2475123, 3089709)),
//        Sensor(location = Position(3546858, 3085529), closestBeacon = Position(3629407, 2984857)),
//        Sensor(location = Position(3542939, 2742086), closestBeacon = Position(3629407, 2984857)),
//        Sensor(location = Position(2010918, 2389107), closestBeacon = Position(2715626, 2000000)),
//        Sensor(location = Position(3734968, 3024964), closestBeacon = Position(3629407, 2984857)),
//        Sensor(location = Position(2219206, 337159), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(1969253, 890542), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(3522991, 3257032), closestBeacon = Position(3629407, 2984857)),
//        Sensor(location = Position(2303155, 3239124), closestBeacon = Position(2475123, 3089709)),
//        Sensor(location = Position(2574308, 111701), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(14826, 2490395), closestBeacon = Position(772918, 2626448)),
//        Sensor(location = Position(3050752, 2366125), closestBeacon = Position(2715626, 2000000)),
//        Sensor(location = Position(3171811, 2935106), closestBeacon = Position(3629407, 2984857)),
//        Sensor(location = Position(3909938, 1033557), closestBeacon = Position(3493189, -546524)),
//        Sensor(location = Position(1955751, 452168), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(2159272, 614653), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(3700981, 2930103), closestBeacon = Position(3629407, 2984857)),
//        Sensor(location = Position(3236266, 3676457), closestBeacon = Position(3373823, 4223689)),
//        Sensor(location = Position(3980003, 3819278), closestBeacon = Position(3373823, 4223689)),
//        Sensor(location = Position(1914391, 723058), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(474503, 1200604), closestBeacon = Position(-802154, 776650)),
//        Sensor(location = Position(2650714, 3674470), closestBeacon = Position(2505629, 4282497)),
//        Sensor(location = Position(1696740, 586715), closestBeacon = Position(1929144, 529341)),
//        Sensor(location = Position(3818789, 2961752), closestBeacon = Position(3629407, 2984857))
//    )

    fun partOne(rowOfInterest: Int): Int {
        val positionSet = mutableSetOf<Position>()
        sensors.map { sensor ->
            positionSet.addAll(sensor.positionsInRange(rowOfInterest))
        }
        val beacons = sensors.map { sensor -> sensor.closestBeacon }
        val locations = sensors.map { sensor -> sensor.location }
        val something = positionSet
            .filter { position -> position.y == rowOfInterest }
            .filter { position -> position !in locations }
            .filter { position -> position !in beacons }
        return something.size
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day15_2022.txt")
    val solver = DayFifteen(input)
    println(solver.partOne(rowOfInterest = 2000000))
}
