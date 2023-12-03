object Day3 {

    val numberRegex = "\\d+".toRegex()
    val symbolRegex = "[^\\d.]+".toRegex() // not a digit or a dot
    val gearRegex = "\\*".toRegex()

    data class EnginePart(
        val value: Int,
        val line: Int,
        val position: IntRange,
    )

    fun EnginePart.touches(line: Int, position: Int) =
        this.line in (line - 1..line + 1) &&
            this.position.intersect(position - 1..position + 1).any()

    fun part1(input: String): Int {
        val parts = parseEngineParts(input)

        return input.lines()
            .flatMapIndexed { line, l ->
                symbolRegex.findAll(l).flatMap { symbol ->
                    parts.filter { it.touches(line, symbol.range.first) }
                }
            }.sumOf { it.value }
    }

    fun part2(input: String): Int {
        val parts = parseEngineParts(input)
        return input.lines()
            .flatMapIndexed { line, l ->
                gearRegex.findAll(l).map { gear ->
                    parts.filter { it.touches(line, gear.range.first) }
                        .toGearRatio()
                }
            }.sum()
    }

    fun List<EnginePart>.toGearRatio(): Int = when (size) {
        2 -> get(0).value * get(1).value
        else -> 0
    }

    fun parseEngineParts(input: String): List<EnginePart> = input.lines()
        .flatMapIndexed { line, l ->
            numberRegex.findAll(l).map { EnginePart(it.value.toInt(), line, it.range) }
        }
}
