package aoc.y2022

import aoc.lib.Resources.fileAsString

class DayTwentyOne(private val input: String) {

    sealed interface Monkey {
        val name: String
    }

    data class MathMonkey(
        override val name: String,
        val number: Long
    ) : Monkey

    data class TeamMonkey(
        override val name: String,
        val left: String,
        val right: String,
        val op: String
    ) : Monkey {

        fun operate(left: Long, right: Long): Long =
            when (op) {
                "+" -> left + right
                "-" -> left - right
                "*" -> left * right
                "/" -> left / right
                else -> throw UnsupportedOperationException("Monkey don't play that.")
            }
    }

    val monkeyMap: Map<String, Monkey> = input
        .split("\n")
        .map {
            val name = it.substringBefore(":")
            val rest = it.substringAfter(":").trim()
            when {
                rest.isNumeric() -> MathMonkey(name = name, number = rest.toLong())
                else -> {
                    val (left, op, right) = rest.split(" ")
                    TeamMonkey(
                        name,
                        left,
                        right,
                        op
                    )
                }
            }
        }.associateBy { it.name }

    fun String.isNumeric(): Boolean = this.all { char -> char.isDigit() }

    fun yell(name: String, monkeys: Map<String, Monkey>): Long {
        val monkey: Monkey = monkeys[name]!!
        return when (monkey) {
            is MathMonkey -> monkey.number
            is TeamMonkey -> {
                monkey.operate(yell(monkey.left, monkeys), yell(monkey.right, monkeys))
            }
        }
    }

    fun partOne(): Long = yell("root", monkeyMap)

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("2022/day21_2022.txt")
    val solver = DayTwentyOne(input)
    println(solver.partOne())
}
