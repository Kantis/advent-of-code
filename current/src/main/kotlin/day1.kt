package se.codeboss.aoc2023

import dev.databoss.aoc.lib.findLast

object Day1 {

    fun part1(input: String) =
        input.lines().sumOf { "${it.firstDigit()}${it.lastDigit()}".toInt() }

    fun String.firstDigit() = first(Char::isDigit)
    fun String.lastDigit() = last(Char::isDigit)

    val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val digitRegex = (digits + "\\d").joinToString("|", prefix = "(", postfix = ")").toRegex()

    fun part2(input: String) =
        input.lines().sumOf { line ->
            10 * digitRegex.find(line)!!.parseDigit() +
                digitRegex.findLast(line).parseDigit()
        }

    fun MatchResult.parseDigit() =
        when (value.length) {
            1 -> value.toInt()
            else -> digits.indexOf(value) + 1
        }
}
