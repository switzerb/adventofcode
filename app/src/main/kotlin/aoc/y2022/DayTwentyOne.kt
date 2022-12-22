package aoc.y2022

import aoc.lib.Resources.fileAsString

class DayTwentyOne(private val input: String) {

    data class Monkey(
        val name: String,
        val num: Int?,
        val op: String?
    )

    val parsed = listOf(
        Monkey(name = "root", num = null, op = "pppw +sjmn"),
        Monkey(name = "dbpl", num = 5, op = null),
        Monkey(name = "cczh", num = null, op = "sllz + lgvd"),
        Monkey(name = "zczc", num = 2, op = null),
        Monkey(name = "ptdq", num = null, op = "humn - dvpt"),
        Monkey(name = "dvpt", num = 3, op = null),
        Monkey(name = "lfqf", num = 4, op = null),
        Monkey(name = "humn", num = 5, op = null),
        Monkey(name = "ljgn", num = 2, op = null),
        Monkey(name = "sjmn", num = null, op = "drzm * dbpl"), //
        Monkey(name = "sllz", num = 4, op = null),
        Monkey(name = "pppw", num = null, op = "cczh / lfqf"),
        Monkey(name = "lgvd", num = null, op = "ljgn * ptdq"),
        Monkey(name = "drzm", num = null, op = "hmdt - zczc"),
        Monkey(name = "hmdt", num = 32, op = null)
    )

    fun partOne(): Int {
        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day21_2022.txt")
    val solver = DayTwentyOne(input)
    println(solver.partOne())
}
