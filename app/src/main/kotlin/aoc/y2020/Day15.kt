package aoc.y2020

data class Turn(val turn: Int, val number: Int)

class Day152020(val input: List<Int>) {

    private val start = input.toMutableList()
    private val said = mutableListOf<Turn>()

    fun say(turn: Turn): Boolean = said.add(turn)

    fun getPrev(idx: Int) = said[idx-1].number

    fun dequeue(idx: Int) {
        say(Turn(idx + 1,start[0]) )
        start.removeAt(0)
    }

    fun getNextNumber(idx: Int) : Turn {
        val repeated = said.filter { it.number == getPrev(idx) }
        val len = repeated.size
        return if(len == 1) {
            Turn(idx+1,0)
        } else {
            Turn(idx+1,repeated[len - 1].turn - repeated[len - 2].turn)
        }

    }

    fun partOne(): Int {
        (0..2020).map { idx -> if(start.isNotEmpty())  dequeue(idx)  else  say(getNextNumber(idx)) }
        return said[2019].number
    }
}

fun main(args: Array<String>) {
  val solver = Day152020(listOf(2,1,10,11,0,6))
    println(solver.partOne()) //232
}
