package aoc.y2021
import aoc.lib.Resources.fileAsList

class DayEight(private val input: List<String>) {

    var patterns = mutableListOf<String>()
    var output = mutableListOf<String>()

    val ONE = 2
    val FOUR = 4
    val SEVEN = 3
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

    fun String.sort() = this.toSortedSet().joinToString("")

    fun getTranslated(digits: List<String>, output: List<String>): Int {
        val numbers = arrayOfNulls<String>(10)

        val one = digits.find { it.length == ONE }?.sort()!!
        val seven = digits.find { it.length == SEVEN }?.sort()!!
        val four = digits.find { it.length == FOUR }?.sort()!!
        val eight = digits.find { it.length == EIGHT }?.sort()!!
        val fiveSet = digits.filter { it.length == 5 }.map { digit -> digit.sort() }
        val sixSet = digits.filter { it.length == 6 }.map { digit -> digit.sort() }

        val three = fiveSet.find { digit -> digit.toSet().containsAll(one.toSet()) }!!
        val nine = sixSet.find { digit -> digit.toSet().containsAll(four.toSet()) }!!

        val five_or_two = fiveSet.filter { each -> each != three }
        val six_or_zero = sixSet.filter { digit -> digit != nine }
        val missing = eight.toSet().subtract(nine.toSet())
        val two = five_or_two.find { each -> each.contains(missing.first()) }!!
        val five = five_or_two.find { each -> each != two }!!

        val six = six_or_zero.find { each -> each.toSet().containsAll(five.toSet()) }!!
        val zero = six_or_zero.find { each -> each != six }!!

        numbers[0] = zero.sort()
        numbers[1] = one.sort()
        numbers[2] = two.sort()
        numbers[3] = three.sort()
        numbers[4] = four.sort()
        numbers[5] = five.sort()
        numbers[6] = six.sort()
        numbers[7] = seven.sort()
        numbers[8] = eight.sort()
        numbers[9] = nine.sort()

        val translated = output.map {
            numbers.indexOf(it)
        }
        println(translated)
        return translated.joinToString("").toInt()
    }

    fun partTwo(): Int {
        val something = input.map { line ->
            val (left, right) = line
                .split("|")
                .map { it.trim() }
            val digits = left.split(" ").map { it.trim() }
            val output = right.split(" ").map { it.trim().toSortedSet().joinToString("") }
            getTranslated(digits, output)
        }
        return something.sum()
    }
}

fun main(args: Array<String>) {
    val input = fileAsList("day08_2021.txt")
    val solver = DayEight(input)
//    println(solver.partOne())
    println(solver.partTwo())
}
