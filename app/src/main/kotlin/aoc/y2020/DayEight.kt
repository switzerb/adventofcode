
data class Instruction(val type: String, val amount: Int ) {}

class DayEight2020(val input: String) {

    private val instr = parseInput()
    val nothing: Unit = Unit

    private fun parseInput(): List<Instruction> {
        return listOf(
                Instruction("nop", 0),
                Instruction("acc", 1),
                Instruction("jmp", -4))
    }

    fun partOne() : Int {
        runInstructions()
        return 0
    }

    private fun runInstructions() : Int {
        val curr = 0 //index of list
        val accVal = 0
        val notLoop = true
        val visited = mutableListOf<Int>() // list of index values we have visited

        //        val accValue = 0
        while (notLoop) {
            // do a thing, starting with the current position
            visited.add(1)
//            switch statement of instructions
            val current = instr[curr]
            when(current.type) {
                "acc" -> acc(current.amount)
                "jmp" -> jmp(current.amount)
                "nop" -> nothing
                else -> nothing
            }
            println(curr)
        }
        return 0
    }

    // I take an amount and return a new current position
    private fun jmp(amount: Int) : Int {
        return 0
    }

    // I take an instruction amount and return a new accumulated value
    private fun acc(amount: Int) : Int {
        return 0
    }
}

fun main(args: Array<String>) {
    println("hello")
    // accumulator
}
