package aoc.y2021
import aoc.lib.Resources.fileAsString

typealias Histogram = Map<String, Int>

class DayFourteen(private val input: String) {

    val template = input.lines().first()

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

    fun run(steps: Int): Int {
        val pairs = template.toHistogram()
        val lastChar = template.last()
        val expanded = (0..steps).fold(pairs) { polymer, _ ->
            expandPolymer(polymer)
        }
        val built = buildPolymer(expanded, lastChar)
        val counts = occurances(built)
        println(counts)
        return 0
    }

    fun expandPolymer(histogram: Histogram): Histogram {
        val polymer = mutableMapOf<String, Int>()
        histogram.forEach { (pair, count) ->
            val element = rules[pair]
            val first_key = "${pair.first()}$element"
            val second_key = "$element${pair.last()}"
            polymer.put(first_key, histogram.getOrDefault(first_key, 0) + count)
            polymer.put(second_key, histogram.getOrDefault(second_key, 0) + count)
        }
        return polymer
    }

    fun partOne(): Int = run(1)

    fun buildPolymer(pairs: Histogram, lastChar: Char): String {
        val chars = pairs.map { (pair, _) ->
            pair.first()
        }
        return chars.plus(lastChar).joinToString("")
    }

    fun occurances(polymer: String): Map<Char, Int> {
        return polymer
            .groupingBy { it }
            .eachCount()
    }

    fun partTwo() {}

    fun String.toHistogram() = template.windowed(2, 1)
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value }
}

fun main(args: Array<String>) {
    val input = fileAsString("day14_2021.txt")
    val solver = DayFourteen(input)
    println(solver.partOne())
}
