package aoc.y2020

data class Rule(val bag: String, val children: Map<String, Int>) {}

class DaySeven2020(val input: String) {

    private fun parser(): List<Rule> {
        return input
                .lines()
                .map { instr ->
                    instr.split("contain")
                    Rule("light red bags", mapOf("bright white bag" to 1, "muted yellow bags" to 2))
                }
    }

//    You have a shiny gold bag. If you wanted to carry it in at least one other bag,
//    how many different bag colors would be valid for the outermost bag?
//    (In other words: how many colors can, eventually, contain at least one shiny gold bag?)
    fun partOne():Int {
//        val rules = parser()
    val one = Rule("light red", mapOf("bright white" to 1, "muted yellow" to 2))
    val two = Rule("muted yellow", mapOf("shiny gold" to 2, "faded blue" to 9))
    val rules = listOf(one, two)
    // loop over rule and check which rule.children contains key "shiny gold"
    // for each kind of bag, count which ones have a shiny gold in their map
        println(rules)
        return 0
    }
}

fun main(args: Array<String>) {
    println("hello world")
}
