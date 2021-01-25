package aoc.y2020

import aoc.lib.Resources.fileAsList

class Day18(val input: List<String>) {

    private val expressions = input.map { it.replace(" ", "") }

    private fun eval(exp: CharIterator) : Long {
        val nums = mutableListOf<Long>()
        var op = '_'
        while(exp.hasNext()) {
            when(val next = exp.nextChar()) {
                '+' -> op = next
                '*' -> op = next
                '(' -> nums.add(eval(exp))
                ')' -> break
                else -> nums.add(next.toString().toLong())
            }
            if(nums.size == 2) {
//                println(nums)
                val (a,b) = nums
                nums.clear()
                nums.plusAssign(if (op == '+') a + b else a * b)
            }
        }
        return nums.first()
    }

    fun partOne(): Long {
        return expressions.sumOf { exp -> eval(exp.iterator()) }
    }
}

fun main(args :Array<String>) {
    val solver = Day18(fileAsList("day18_2020.txt"))
    println(solver.partOne())
}
