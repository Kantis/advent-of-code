object Day3 {

    fun part1(input: String): Int {
        return input.lines()
            .map(::compartmentalize)
            .map { (a, b) -> a.first { it in b } }
            .sumOf { priority(it) }
    }

    fun part2(input: String): Int {
        return input.lines()
            .chunked(3)
            .map { (a, b, c) -> a.first { it in b && it in c } }
            .sumOf { priority(it) }
    }

    fun priority(c: Char) = priorities.indexOf(c) + 1
    val priorities = ('a'..'z') + ('A'..'Z')

    fun compartmentalize(rucksack: String): Pair<String, String> =
        rucksack
            .chunked(rucksack.length / 2)
            .let { (fst, snd) -> fst to snd }
}
