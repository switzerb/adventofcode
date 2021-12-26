package aoc.y2021

import aoc.lib.Resources.fileAsString

// Packet is a tree
data class Packet(
    val version: Int,
    val type: Int,
    val totalBits: Int,
    val value: Long? = null,
    val subPackets: List<Packet> = emptyList()
) {

    fun sumVersions(): Int {
        if (subPackets.isEmpty()) {
            return version
        }
        return version + subPackets.sumOf(Packet::sumVersions)
    }
}

class DaySixteen(private val input: String) {

    companion object {
        fun toBinary(hex: String): String {
            return hex.toCharArray().map {
                binaryLookup[it]
            }.joinToString("")
        }

        private val binaryLookup: Map<Char, String> = mapOf(
            '0' to "0000",
            '1' to "0001",
            '2' to "0010",
            '3' to "0011",
            '4' to "0100",
            '5' to "0101",
            '6' to "0110",
            '7' to "0111",
            '8' to "1000",
            '9' to "1001",
            'A' to "1010",
            'B' to "1011",
            'C' to "1100",
            'D' to "1101",
            'E' to "1110",
            'F' to "1111",
        )
    }

    private var idx = 0
    private val message = toBinary(input)

    fun inc(n: Int): Int {
        val temp = idx + n
        idx += n
        return temp
    }

    private fun String.getHeaders(): Pair<Int, Int> {
        val version = substring(idx, inc(3)).toInt(2)
        val type = substring(idx, inc(3)).toInt(2)
        return Pair(version, type)
    }

    private fun getLiteralValue(): Long {
        var value = ""
        do {
            val prefix = message.substring(idx, inc(1)).toInt()
            value += message.substring(idx, inc(4))
        } while (prefix != 0)
        return value.toLong(2)
    }

    private fun getSubPacketLen() = message.substring(idx, inc(15)).toInt(2)
    private fun getSubPacketCount() = message.substring(idx, inc(11)).toInt(2)
    private fun getLengthId(): Int = message.substring(idx, inc(1)).toInt()

    fun processPacket(): Packet {
        val startBitCount = idx
        val (version, type) = message.getHeaders()

        // literal value
        if (type == 4) {
            val literal = getLiteralValue()
            return Packet(
                version = version,
                type = type,
                totalBits = idx - startBitCount,
                value = literal,
            )
            // operator with subPackets
        } else {
            val lengthId = getLengthId()
            val subPacketLen = when (lengthId) {
                0 -> getSubPacketLen()
                1 -> getSubPacketCount()
                else -> 0
            }
            var count = 0
            val subPackets = mutableListOf<Packet>()

            while (subPacketLen > count) {
                val packet = processPacket()
                count += when (lengthId) {
                    0 -> packet.totalBits
                    1 -> 1
                    else -> 0
                }
                subPackets.add(packet)
            }

            return Packet(
                version = version,
                type = type,
                totalBits = idx - startBitCount,
                subPackets = subPackets
            )
        }
    }

    fun partOne(): Int = processPacket().sumVersions()

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day16_2021.txt")
    val solver = DaySixteen(input)
    println(solver.partOne())
}
