package aoc.y2021
import aoc.lib.Resources.fileAsList

class DayThree(private val input: List<String>, val width: Int = 12) {

    val n = width - 1

    fun getRate(compare: String): String {
        val rate = mutableListOf<Char>()
        (0..n).map { idx ->
            val histogram = input
                .groupingBy { it[idx] }
                .eachCount()

            val max = histogram.maxByOrNull { it.value }
            val min = histogram.minByOrNull { it.value }

            when (compare) {
                "more" -> rate.add(max?.key!!)
                "less" -> rate.add(min?.key!!)
                else -> null
            }
        }
        return rate.joinToString("")
    }

    fun partOne(): Int {
        val gammaDecimal = getRate("more").toInt(2)
        val epsilonDecimal = getRate("less").toInt(2)
        return gammaDecimal * epsilonDecimal
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsList("day03_2021.txt")
    val solver = DayThree(input)
    println(solver.partOne()) // 2640986
}
