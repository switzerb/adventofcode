package aoc.y2020

import com.google.common.graph.GraphBuilder
import com.google.common.graph.MutableGraph

data class Bag(val bag: String, val containedBy: Map<String, Char>) {}

class DaySeven2020(val input: String) {

    private fun parseBag(input: String): String {
        return input.replace("(bags|bag|[.])".toRegex(), "")
    }

    private fun parser(): List<Bag> {
        return input
                .lines()
                .map {
                    val parts = it.split("contain")
                    val bag = parseBag(parts[0])
                    Bag(bag, emptyMap())

//                    if(instr[1] == " no other bags.") {
//                        Rule(instr[0].replace("(bags|bag|[.])".toRegex(), ""), emptyMap())
//                    } else {
//                        val children = instr[1].split(",")
//                        val something = mutableMapOf<String, Char>()
//                        children.forEach { bags ->
//                            val count = bags.trim().first()
//                            val bag = bags
//                                    .trim()
//                                    .substring(2)
//                                    .replace("(bags|bag|[.])".toRegex(), "")
//                                    .trim()
//                                something[bag] = count
//                        }
//                        Rule(instr[0].replace("(bags|bag|[.])".toRegex(), ""), something)
//                    }
                }
    }

//    You have a shiny gold bag. If you wanted to carry it in at least one other bag,
//    how many different bag colors would be valid for the outermost bag?
//    (In other words: how many colors can, eventually, contain at least one shiny gold bag?)
    fun partOne():Int {
    val rules = parser()
    return rules
            .mapNotNull { it.containedBy["shiny gold"] }
            .size
    }
}

fun main(args: Array<String>) {
    println("hello world")

    val bags: MutableGraph<String> = GraphBuilder.directed().build()

    with(bags) {
        addNode("gold")
        addNode("red")
        addNode("blue")
    }
    bags.putEdge("red", "gold")
    println("jesus")
}
