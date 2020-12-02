package aoc.y2020

class Password(
        val min: Int,
        val max: Int,
        val letter: Char,
        val text: String
        ) {

    fun isValid(): Boolean {
        val letters : String = text.filter { it == letter }
        return letters.length in min..max;
    }
}

class DayTwo2020(private val input: String) {

    private val passwords = mutableListOf<Password>()

    fun parser(): Unit {
        val lines = input.trim().lines()
        lines.forEach {
            val parts = it.split(" ")
            val min = parts[0].substringBefore("-").toInt()
            val max = parts[0].substringAfter("-").toInt()
            val letter = parts[1].first()
            val text = parts[2]
            passwords.add(Password(min,max,letter,text))
        }
    }

    fun partOne(): Int {
        parser()
        val valid = passwords.filter { it.isValid() }
        return valid.size
    }
}

fun main(args: Array<String>) {
    val cl = DayTwo2020::class.java.classLoader.getResource("day2_2020.txt") ?: return
    val input = cl.readText();
    val solver = DayTwo2020(input)
    println(solver.partOne()) //524
}
