package aoc.y2021
import aoc.lib.Resources.fileAsString

// LIFO
class Stack {
    val stack: MutableList<Char> = mutableListOf()

    fun push(item: Char) = stack.add(0, item)

    fun pop(): Char = stack.removeAt(0)

    fun peek(): Char = stack.get(0)

    fun isEmpty(): Boolean = stack.size == 0
}

class DayTen(private val input: String) {

    val scoring = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    val corrupted = mutableListOf<Char>()

    fun partOne(): Int {
        val chunks = input.parse()
        chunks.map { chunk ->
            val stack = Stack()
            chunk.forEach { char ->
                when (char) {
                    '[' -> stack.push(char)
                    '<' -> stack.push(char)
                    '(' -> stack.push(char)
                    '{' -> stack.push(char)
                    else -> {
                        val match = stack.pop()
                        if (char == ']' && match != '[') {
                            corrupted.add(char)
                        }
                        if (char == '>' && match != '<') {
                            corrupted.add(char)
                        }
                        if (char == ')' && match != '(') {
                            corrupted.add(char)
                        }
                        if (char == '}' && match != '{') {
                            corrupted.add(char)
                        }
                    }
                }
            }
        }
        // if the stack is empty the chunk is valid
        val counts = corrupted.groupingBy { it }.eachCount()
        var total = 0
        counts.forEach { char, count ->
            total += scoring[char]?.times(count) ?: 0
        }
        return total
    }

    fun chunkIsValid(): Boolean {
        return false
    }

    fun partTwo() {}

    private fun String.parse() = lines().map {
        it.toCharArray()
    }
}

fun main(args: Array<String>) {
    val input = fileAsString("day10_2021.txt")
    val solver = DayTen(input)
    println(solver.partOne())
}
