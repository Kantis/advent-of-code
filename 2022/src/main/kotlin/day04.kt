object Day4 {

    val parseRegex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

    fun parse1(line: String) = parseRegex
        .matchEntire(line)
        .let { matches ->
            matches!!.groupValues.let { (_, a, b, c, d) ->
                Pair(IntRange(a.toInt(), b.toInt()), IntRange(c.toInt(), d.toInt()))
            }
        }

    fun part1(input: String): Int {
        return input.lines()
            .map(::parse1)
            .count { (a, b) -> a.all { it in b } || b.all { it in a } }
    }

    fun part2(input: String): Int {
        return input.lines()
            .map(::parse1)
            .count { (a, b) -> a.intersect(b).isNotEmpty() }
    }
}
