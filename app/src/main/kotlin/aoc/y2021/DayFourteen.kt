package aoc.y2021
import aoc.lib.Resources.fileAsString

typealias Histogram = Map<String, Long>

class DayFourteen(private val input: String) {

    val lastElement = 'B'
    val template = "NNCB"
    val start = template.toHistogram()

    val rules: Map<String, String> = mapOf(
        "CH" to "B",
        "HH" to "N",
        "CB" to "H",
        "NH" to "C",
        "HB" to "C",
        "HC" to "B",
        "HN" to "C",
        "NN" to "C",
        "BH" to "H",
        "NC" to "B",
        "NB" to "B",
        "BN" to "B",
        "BB" to "N",
        "BC" to "B",
        "CC" to "N",
        "CN" to "C",
    )

    fun run(steps: Int): Long {
        return (0..steps)
            .fold(start) { polymer, _ -> polymer.calculate() }
            .occurances()
            .values
            .sorted()
            .let { it.last() - it.first() }
    }

    fun Histogram.calculate(): Histogram {
        val polymers = mutableMapOf<String, Long>()
        forEach { pair, count ->
            val element = rules.getValue(pair)
            val inc1 = polymers.getOrDefault("${pair.first()}$element", 0L) + count
            val inc2 = polymers.getOrDefault("$element${pair.last()}", 0L) + count
            polymers.set("${pair.first()}$element", inc1)
            polymers.set("$element${pair.last()}", inc2)
        }
        return polymers.toMap()
    }

    fun Histogram.occurances(): Map<Char, Long> {
        return this.map { (pair, count) ->
            pair.first() to count
        }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.sum() + if (it.key == lastElement) 1 else 0 }
    }

    fun partOne(): Long = run(10)

    fun partTwo() {}

    fun String.toHistogram() = template.windowed(2, 1)
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value.toLong() }
}

fun main(args: Array<String>) {
    val input = fileAsString("day14_2021.txt")
    val solver = DayFourteen(input)
    println(solver.partOne())
}
