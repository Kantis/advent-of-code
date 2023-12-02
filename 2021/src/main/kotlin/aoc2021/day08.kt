package aoc2021

private typealias Digit = Set<Char>

object Day8 {

    fun part1(lines: Sequence<String>): Int =
        lines.map { it.split("|")[1].trim() }
            .flatMap { it.split(" ") }
            .count { it.length in arrayOf(2, 3, 4, 7) }

    fun decode(inputs: List<Digit>): Map<Digit, Int> {
        val segmentCounts: Map<Int, List<Digit>> = inputs.groupBy { it.size }

        val one = segmentCounts[2]!!.single()
        val eight = segmentCounts[7]!!.single()
        val four = segmentCounts[4]!!.single()
        val seven = segmentCounts[3]!!.single()
        val three = segmentCounts[5]!!.single { it.containsAll(one) }
        val nine = three + four
        val zero = segmentCounts[6]!!.single { it.containsAll(one) && it != nine }
        val six = segmentCounts[6]!!.single { it != nine && it != zero }
        val five = segmentCounts[5]!!.single { nine.containsAll(it) }
        val two = segmentCounts[5]!!.single { it != three && it != five }

        return mapOf(
            zero to 0,
            one to 1,
            two to 2,
            three to 3,
            four to 4,
            five to 5,
            six to 6,
            seven to 7,
            eight to 8,
            nine to 9
        )
    }

    fun part2(lines: Sequence<String>): Int =
        lines.map {
            it.split("|").let { tokens ->
                decode(tokens[0].trim().split(" ").map { it.toSet() }) to tokens[1].trim().split(" ")
            }
        }
            .map { (cipher, tokens) -> tokens.map { cipher[it.toSet()]!! }.joinToString(separator = "").toInt() }
            .sum()
}
