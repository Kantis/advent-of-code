package dev.databoss.aoc.lib

fun Regex.findLast(s: String): MatchResult {
    for (revOffset in s.length - 1 downTo 0) {
        val result = find(s, revOffset)
        if (result != null) return result
    }
    error("No match found")
}
