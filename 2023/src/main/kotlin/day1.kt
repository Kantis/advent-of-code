object Day1 {

    fun part1(input: String) =
        input.split("\n\n")
            .map { it.split("\n") }
            .map { it.sumOf { it.toInt() } }
            .max()

    fun part2(input: String) =
        input.split("\n\n")
            .map { it.lines().sumOf { line -> line.toInt() } }
            .sortedDescending()
            .take(3)
            .sum()
}
