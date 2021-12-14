object Day3 {

    private fun mostCommon(lines: List<String>, pos: Int) =
        if (2 * lines.count { it[pos] == '1' } >= lines.size) '1' else '0'

    private fun gamma(lines: List<String>): String =
        lines[0].indices.map { pos ->
            mostCommon(lines, pos)
        }.joinToString(separator = "")

    fun part1(lines: List<String>): Long {
        val tot = 2.pow(lines[0].count()) - 1
        val gamma = gamma(lines).binaryToInt()
        val epsilon = tot - gamma
        return gamma * epsilon
    }

    private fun Char.invert() = when (this) {
        '1' -> '0'
        '0' -> '1'
        else -> error("Invalid char")
    }

    private fun bitSearch(lines: List<String>, searchMostCommon: Boolean, pos: Int): String {
        if (lines.size == 1) return lines[0]

        val filterChar = when (searchMostCommon) {
            true -> mostCommon(lines, pos)
            false -> mostCommon(lines, pos).invert()
        }

        return bitSearch(lines.filter { it[pos] == filterChar }, searchMostCommon, pos + 1)
    }

    private fun oxygenRating(lines: List<String>) = bitSearch(lines, true, 0).binaryToInt()
    private fun co2Rating(lines: List<String>) = bitSearch(lines, false, 0).binaryToInt()

    fun part2(lines: List<String>) = oxygenRating(lines) * co2Rating(lines)
}

