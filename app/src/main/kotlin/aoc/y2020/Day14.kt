package aoc.y2020

import aoc.lib.Resources.fileAsList

class Day14y2020 (val input: List<String>) {

    private val memory: MutableMap<Long, Long> = mutableMapOf()
    private val base = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    fun partOne(): Long {
        var mask = base
        input.forEach { instruction ->
            if (instruction.startsWith("mask")) {
                mask = instruction.substringAfter("= ")
            } else {
                val address = instruction.substringAfter("[").substringBefore("]").toLong()
                val value = instruction.substringAfter("= ")
                memory[address] = masked(value, mask)
            }
        }
        return memory.values.sum()
    }

    private fun masked(value: String, mask: String): Long =
        value
            .toLong()
            .toString(2)
            .padStart(36, '0')
            .zip(mask)
            .map { (valueChar, maskChar) ->
                maskChar.takeUnless { it == 'X' } ?: valueChar
            }
            .joinToString("")
            .toLong(2)


}

fun main(args: Array<String>) {
    val solver = Day14y2020(fileAsList("day14_2020.txt"))
    println(solver.partOne())
}
