package aoc.y2022

typealias Item = Int

class Monkey(
    val id: Int,
    val items: List<Item>,
    val divider: Int,
    val worryOperation: (worry: Int) -> Int,
    val monkeyTId: Int,
    val monkeyFId: Int
) {
    private var itemCounter = 0
    val holding = items.toMutableList()

    // while there is anything in the list, throw until holding nothing
    fun throwTo(): Int {
        val item = holding.first()
        itemCounter++
        val newWorries = worryOperation(item).floorDiv(3)
        holding.remove(item)
        if (newWorries % this.divider == 0) {
            return monkeyTId
        } else {
            return monkeyFId
        }
    }
}

class DayEleven(private val monkeys: List<Monkey>) {

//    val monkeys = buildList {
//
//    }

    fun round() {
        for (id in monkeys.indices) {
            val next = monkeys[id].throwTo()
            println(monkeys[id].id)
        }
    }

    fun partOne(): Int {
        round()
        return 0
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
//    val input = fileAsString("2022/day11_2022.txt")
//    val solver = DayEleven(input)
//    println(solver.partOne())
}
