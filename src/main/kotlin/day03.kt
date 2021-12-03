object Day3 {

    private fun mostCommon(lines: List<String>, pos: Int) =
        lines.count().let { count ->
            if (2 * lines.count { it[pos] == '1' } >= count) '1' else '0'
        }

    private fun gamma(lines: List<String>): String =
        (0 until lines[0].length).map { pos ->
            mostCommon(lines, pos)
        }.joinToString(separator = "")

    fun part1(lines: List<String>): Long {
        val tot = 2.pow(lines[0].count()) - 1
        val gamma = gamma(lines).binaryToInt()
        val epsilon = tot - gamma
        return gamma * epsilon
    }

    private fun bitSearch(lines: List<String>, searchMostCommon: Boolean, pos: Int): String {
        if (lines.size == 1) return lines[0]

        val filterChar = when (searchMostCommon) {
            true -> mostCommon(lines, pos)
            false -> if (mostCommon(lines, pos) == '1') '0' else '1'
        }

        return bitSearch(lines.filter { it[pos] == filterChar }, searchMostCommon, pos + 1)
    }

    private fun oxygenRating(lines: List<String>) = bitSearch(lines, true, 0).binaryToInt()
    private fun co2Rating(lines: List<String>) = bitSearch(lines, false, 0).binaryToInt()

    fun part2(lines: List<String>): Int {
        return oxygenRating(lines) * co2Rating(lines)
    }
}

fun main() {
    Day3.part1(generateSequence(::readLine).toList())
}
