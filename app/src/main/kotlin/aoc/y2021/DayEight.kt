package aoc.y2021
import aoc.lib.Resources.fileAsList

class DayEight(private val input: List<String>) {

    var patterns = mutableListOf<String>()
    var output = mutableListOf<String>()

    val ONE = 2
    val SEVEN = 3
    val FOUR = 4
    val EIGHT = 7

    fun parse() {
        input.map { line ->
            val (left, right) = line
                .split("|")
                .map { it.trim() }
            val p = left.split(" ").map { it.trim() }
            val o = right.split(" ").map { it.trim() }
            patterns.addAll(p)
            output.addAll(o)
        }
    }

    fun partOne(): Int {
        parse()
        val uniques = output.filter { signal ->
            signal.length == ONE ||
                signal.length == SEVEN ||
                signal.length == FOUR ||
                signal.length == EIGHT
        }
        return uniques.size
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsList("day08_2021.txt")
    val solver = DayEight(input)
    println(solver.partOne())
}
