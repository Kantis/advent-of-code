package aoc2021

sealed interface Packet {
    val version: Int
    val type: Int
}

data class LiteralPacket(
    override val version: Int,
    val value: Long
) : Packet {
    override val type = 4
}

data class OpPacket(
    override val version: Int,
    override val type: Int,
    val length: Length,
    val subpackets: List<Packet>
) : Packet

sealed interface Length {
    val length: Int

    data class NumberOfPackets(override val length: Int) : Length
    data class TotalBits(override val length: Int) : Length
}

fun eval(packet: Packet): Long =
    when (packet) {
        is LiteralPacket -> packet.value
        is OpPacket -> when (packet.type) {
            0 -> packet.subpackets.fold(0L) { acc, subpacket -> acc + eval(subpacket) }
            1 -> packet.subpackets.fold(1L) { acc, subpacket -> acc * eval(subpacket) }
            2 -> packet.subpackets.minOf { eval(it) }
            3 -> packet.subpackets.maxOf { eval(it) }
            5 -> if (eval(packet.subpackets[0]) > eval(packet.subpackets[1])) 1 else 0
            6 -> if (eval(packet.subpackets[0]) < eval(packet.subpackets[1])) 1 else 0
            7 -> if (eval(packet.subpackets[0]) == eval(packet.subpackets[1])) 1 else 0
            else -> error("Unsupported op")
        }
    }


fun String.parseLength() =
    when (val mode = this[6].digitToInt()) {
        0 -> Length.TotalBits(slice(7..(7 + 14)).toInt(2))
        1 -> Length.NumberOfPackets(slice(7..(7 + 10)).toInt(2))
        else -> error("Unsupported length mode $mode")
    }

fun String.parseVersion() = slice(0..2).toInt(2)
fun String.parseLiteralValue() =
    chunked(5)
        .let { chunks ->
            chunks.mapIndexedNotNull { index, s ->
                if (chunks.indexOfFirst { it.startsWith("0") } < index) null
                else s.substring(1)
            }.joinToString(separator = "").let {
                it.toLong(2) to it.length * 5 / 4
            }
        }

fun String.hexToBin() = chunked(4).joinToString(separator = "") {
    it.toInt(16).toString(2).padStart(it.length * 4, '0')
}

fun parseSubpackets(bits: String, length: Length): Pair<List<Packet>, Int> {
    return when (length) {
        is Length.NumberOfPackets -> {
            val result = mutableListOf<Packet>()
            var sub = 18
            for (i in 1..length.length) {
                val (p, consumedLength) = parsePacket(bits.substring(sub))
                sub += consumedLength
                result.add(p)
            }
            result to sub
        }
        is Length.TotalBits -> {
            val available = bits.substring(22, 22 + length.length)
            mutableListOf<Packet>()

            val result = mutableListOf<Packet>()
            var sub = 0
            while (sub < available.length) {
                val (p, consumedLength) = parsePacket(available.substring(sub))
                sub += consumedLength
                result.add(p)
            }
            result to 22 + length.length
        }
    }
}

fun parsePacket(bits: String): Pair<Packet, Int> {
    return when (val type = bits.slice(3..5).toInt(2)) {
        4 -> bits.substring(6).parseLiteralValue().let { (value, length) ->
            LiteralPacket(bits.parseVersion(), value) to 6 + length
        }
        else -> bits.parseLength().let { length ->
            parseSubpackets(bits, length).let { (subpackets, consumed) ->
                OpPacket(bits.parseVersion(), type, length, subpackets) to consumed
            }
        }
    }
}

fun Packet.sumVersions(): Long = version + ((this as? OpPacket)?.subpackets?.sumOf { it.sumVersions() } ?: 0)

object Day16 {

    fun part1(input: String): Long {
        return parsePacket(input.hexToBin()).first.sumVersions()
    }

    fun part2(input: String): Long {
        return eval(parsePacket(input.hexToBin()).first)
    }
}
