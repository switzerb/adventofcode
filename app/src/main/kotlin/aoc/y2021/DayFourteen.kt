package aoc.y2021
import aoc.lib.Resources.fileAsString

typealias Histogram = Map<String, Long>
class DayFourteen(private val input: String) {

    val template = input.lines().first().trim()
    val rules: Map<String, String> = input.parseRules()

    fun run(steps: Int): Long {
        val polymer: String = template

        var histogram = template.toHistogram().toMutableMap()

        val first = polymer.take(2)
        val last = polymer.takeLast(2)

        (1..steps).forEach {
            val next = mutableMapOf<String, Long>()
            for (pair in histogram.keys) {
                val element = rules[pair]!!
                val left = "${pair.first()}$element"
                val right = "$element${pair.last()}"
                next[left] =
                    next.getOrDefault(left, 0) + histogram.getOrDefault(pair, 0)
                next[right] =
                    next.getOrDefault(right, 0) + histogram.getOrDefault(pair, 0)
            }
            histogram = next
        }

        val elements = histogram
            .map { listOf(Pair(it.key[0], it.value)) }
            .flatten()
            .groupBy { it.first }
            .mapValues { p -> p.value.sumOf { it.second } }
            .toMutableMap()
        elements[first[0]] = elements.getOrDefault(first[0], 0) + 1
        elements[last[0]] = elements.getOrDefault(last[0], 0) + 1
        elements[last[1]] = elements.getOrDefault(last[1], 0) + 1
        return elements.values.maxOf { it } - elements.values.minOf { it }
    }

    fun partOne(): Long {
        return run(10)
    }

    fun partTwo(): Long = run(40)

    private fun String.toHistogram() = windowed(2, 1)
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value.toLong() }

    private fun String.parseRules() = lines()
        .drop(2)
        .associate { line ->
            val (key, value) = line.split(" -> ")
            key to value
        }
}

fun main(args: Array<String>) {
    val input = fileAsString("day14_2021.txt")
    val solver = DayFourteen(input)
    println(solver.partOne()) // 2549
    println(solver.partTwo()) // 2516901104210
}
