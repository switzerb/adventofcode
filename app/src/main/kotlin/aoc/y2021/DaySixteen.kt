package aoc.y2021

import aoc.lib.Resources.fileAsString

// Packet is a tree
data class Packet(
    val version: Int,
    val type: Int,
    val totalBits: Int,
    val value: Long? = null,
    val subPackets: List<Packet> = emptyList()
)

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

    fun getLiteralValue(): Long {
        var value = ""
        do {
            val prefix = message.substring(idx, inc(1)).toInt()
            value += message.substring(idx, inc(4))
        } while (prefix != 0)
        return value.toLong(2)
    }

    fun getSubPacketLen() = message.substring(idx, inc(15)).toInt(2)
    fun getSubPacketCount() = message.substring(idx, inc(11)).toInt(2)

    fun getLengthId(): Int = message.substring(idx, inc(1)).toInt()

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
            when (getLengthId()) {
                0 -> {
                    val subpacketLen = getSubPacketLen()
                    var bits = 0
                    val subPackets = mutableListOf<Packet>()

                    while (subpacketLen > bits) {
                        val packet = processPacket()
                        bits += packet.totalBits
                        subPackets.add(packet)
                    }
                    return Packet(
                        version = version,
                        type = type,
                        totalBits = idx - startBitCount,
                        subPackets = subPackets
                    )
                }
                1 -> {
                    val subPacketCount = getSubPacketCount()
                    var count = 0
                    val subPackets = mutableListOf<Packet>()

                    while (subPacketCount > count) {
                        val subPacket = processPacket()
                        subPackets.add(subPacket)
                        count++
                    }
                    return Packet(
                        version = version,
                        type = type,
                        totalBits = idx - startBitCount,
                        subPackets = subPackets
                    )
                }
            }
        }
        throw Error()
    }

    private fun sumVersions(packet: Packet): Int {
        if (packet.subPackets.isEmpty()) {
            return packet.version
        }
        return packet.version + packet.subPackets.sumOf { subPacket ->
            sumVersions(subPacket)
        }
    }

    fun partOne(): Int {
        val root = processPacket()
        return sumVersions(root)
    }

    fun partTwo() {}
}

fun main(args: Array<String>) {
    val input = fileAsString("day16_2021.txt")
    val solver = DaySixteen(input)
    println(solver.partOne())
}
