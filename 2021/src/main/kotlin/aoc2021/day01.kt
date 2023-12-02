package aoc2021

object Day1 {

    fun part1(lines: Sequence<String>): Int =
        lines.map { it.toInt() }
            .windowed(2)
            .count { it[1] > it[0] }

    fun part2(lines: Sequence<String>): Int =
        lines.map { it.toInt() }
            .windowed(4)
            .count { it.slice(1..3).sum() > it.slice(0..2).sum() }
}

