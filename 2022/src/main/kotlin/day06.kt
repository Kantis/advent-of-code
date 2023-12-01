object Day6 {
    fun part1(input: String, size: Int = 4) = input
        .windowedSequence(size, 1)
        .indexOfFirst { it.toSet().size == size } + size

    fun part2(input: String) = part1(input, 14)
}
