package aoc.y2020

const val OVER = 3
const val DOWN = 1

class DayThree2020(private val input: String) {

    private fun parser(): List<String> {
        return input.trim().lines()
    }

    fun partOne():Int {
        val map = parser()
        val depth = map.size - 1
        val width = map[0].length
        var over = 0
        var count = 0
        for(i in 1..depth step DOWN) {
            over+=OVER
            if(over >= width) { over-=width }
            if(map[i][over] == '#') {
                count++
            }
        }
        return count
    }
}

fun main(args: Array<String>) {
    val cl = DayThree2020::class.java.classLoader.getResource("day3_2020.txt")
    val input = cl.readText()
    val solver = DayThree2020(input)
    println(solver.partOne())
}
