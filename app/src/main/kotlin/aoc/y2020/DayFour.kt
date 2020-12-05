package aoc.y2020

data class Passport(val attributes: Map<String,String>) {
//    val byr:String = "", // (Birth Year)
//    val iyr:String = "", // (Issue Year)
//    val eyr:String = "", // (Expiration Year)
//    val hgt:String = "", // (Height)
//    val hcl:String = "", // (Hair Color)
//    val ecl:String = "", // (Eye Color)
//    val pid:String = "", // (Passport ID)
//    val cid:String = ""  // (Country ID)

    private val required = listOf("byr","iyr","eyr","hgt","hcl","ecl","pid")

    fun isComplete():Boolean {
        val valid = required.map { att ->
            attributes.containsKey(att)
        }
        return valid.all { it }
    }
}

class DayFour2020(val input: String) {

    private val passports = parser()

    private fun builder(fields: List<String>): Passport {
        var valuesMap = mutableMapOf<String,String>()
        fields.map {
            val pair = it.split(":")
            valuesMap.put(pair[0],pair[1])
        }
        return Passport(valuesMap)
    }

    fun parser(): List<Passport> {
        val split = input.trim().split("\n\n".toRegex())
        return split.map { item ->
            val fields = item.split("\\s|\n".toRegex())
            builder(fields)
        }
    }

    fun partOne():Int {
        var count = 0
        passports.forEach {
            if(it.isComplete()){
                count++
            }
        }
        return count
    }
}

fun main(args: Array<String>) {
    val cl = DayFour2020::class.java.classLoader.getResource("day4_2020.txt") ?: return
    val input = cl.readText()
    val solver = DayFour2020(input)
    println(solver.partOne()) //210
}
