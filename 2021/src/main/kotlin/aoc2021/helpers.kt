package aoc2021

fun Int.pow(exponent: Int): Long =
    (0 until exponent).map { this.toLong() }.reduce(Long::times)

fun String.binaryToInt() = toInt(2)
