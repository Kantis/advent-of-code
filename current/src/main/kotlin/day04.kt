import dev.databoss.aoc.lib.pow

object Day4 {
    data class ScratchCard(val id: Int, val winningNumbers: Set<Int>, val scratchNumbers: Set<Int>)

    val numberRegex = "\\d+".toRegex()

    fun ScratchCard.countWinningNumbers(): Int {
        return scratchNumbers.intersect(winningNumbers).count()
    }

    fun part1(input: String) = input.lines().map { line ->
        numberRegex.findAll(line)
            .toScratchCard(line.indexOf('|'))
            .countWinningNumbers()
            .let { if (it == 0) 0 else 2.pow(it - 1).toInt() }
    }.sum()

    fun Sequence<MatchResult>.toScratchCard(indexOf: Int) =
        ScratchCard(
            first().value.toInt(),
            drop(1).takeWhile { it.range.first < indexOf }.map { it.value.toInt() }.toSet(),
            dropWhile { it.range.first < indexOf }.map { it.value.toInt() }.toSet(),
        )

    fun part2(input: String) = input.lines().map { line ->
        numberRegex.findAll(line)
            .toScratchCard(line.indexOf('|'))
            .countWinningNumbers()
    }.let { winningNumbersPerCard ->
        println(winningNumbersPerCard)
        val cardCounts = MutableList(winningNumbersPerCard.size) { 1 }
        for (i in winningNumbersPerCard.indices) {
            for (j in 1..winningNumbersPerCard[i]) {
                println("Adding ${cardCounts[i]} copies of card ${j + i + 1}")
                cardCounts[j + i] += cardCounts[i]
            }
        }
        println(cardCounts)
        cardCounts.sum()
    }
}
