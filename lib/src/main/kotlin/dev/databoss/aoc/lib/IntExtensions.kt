package dev.databoss.aoc.lib

fun Int.pow(exponent: Int): Long =
    (0 until exponent).map { this.toLong() }.fold(1, Long::times)