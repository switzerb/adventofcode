package aoc.y2021
import aoc.lib.Resources.fileAsString

class DaySixteen(private val input: String) {

//    0 = 0000
//    1 = 0001
//    2 = 0010
//    3 = 0011
//    4 = 0100
//    5 = 0101
//    6 = 0110
//    7 = 0111
//    8 = 1000
//    9 = 1001
//    A = 1010
//    B = 1011
//    C = 1100
//    D = 1101
//    E = 1110
//    F = 1111

    // a single packet at its outermost layer which itself contains many other packets.
    // Every packet begins with a standard header:
    //  - the first three bits encode the packet version,
    //  - and the next three bits encode the packet type ID.

    fun partOne(): String {

        // convert hex to binary
        // first three bits are version
        // next three bits are typeID

        // packets with typeID 4 represent literal value
       return input
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day16_2021.txt")
    val solver = DaySixteen(input)
    println(solver.partOne())
}
