package aoc.y2020

class Day18(val input: List<String>) {

    val expressions = input.map { it.replace(" ", "") }

    fun eval(exp: CharIterator) : Int {
        val nums = mutableListOf<Int>()
        var op = '_'
        while(exp.hasNext()) {
            when(val next = exp.nextChar()) {
                '+' -> op = next
                '*' -> op = next
                else -> nums.add(next.toString().toInt())
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

    fun partOne(): Int {
        return expressions.sumOf { exp -> eval(exp.iterator()) }
    }
}

fun main(args :Array<String>) {
    println("Hello World")
}
