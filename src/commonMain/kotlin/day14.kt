object Day14 {
    fun parseInput(lines: String): Pair<String, Map<String, String>> {
        return lines.split("\n\n").let { (template, transforms) ->
            template to transforms.split("\n").filter { it.isNotBlank() }.associate { line ->
                line.split(" -> ").let { (sequence, result) ->
                    sequence to result
                }
            }
        }
    }

    fun part1(lines: String, iterations: Int = 10): Int {
        val (template, transforms) = parseInput(lines)
        return (1..iterations).fold(template) { acc, _ ->
            acc.windowed(2).joinToString(prefix = acc[0].toString(), separator = "") { transforms[it]!! + it[1] }
        }.let {
            val byCount = it.groupBy { it }.mapValues { (_, v) -> v.count() }
            println(byCount)
            byCount.maxOf { it.value } - byCount.minOf { it.value }
        }
    }

    fun part2(lines: String): Int = part1(lines, 40)
}
