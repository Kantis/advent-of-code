import Day2.Option.PAPER
import Day2.Option.ROCK
import Day2.Option.SCISSORS

object Day2 {

    operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) =
        Pair(first + other.first, second + other.second)

    enum class Option {
        ROCK, PAPER, SCISSORS
    }

    val options = listOf(ROCK, PAPER, SCISSORS)

    fun parseOption(c: Char) = when (c) {
        'A', 'X' -> ROCK
        'B', 'Y' -> PAPER
        'C', 'Z' -> SCISSORS
        else -> error("Undefined option '$c'")
    }

    fun selectOption(opponentOption: Option, symbol: Char) =
        options[(options.indexOf(opponentOption) + (symbol - 'Y')).mod(3)]

    fun Option.bonusPoints() = options.indexOf(this) + 1

    data class Round(
        val opponentOption: Option,
        val myOption: Option,
    )

    fun calculateScore(round: Round): Pair<Int, Int> = with(round) {
        Pair(opponentOption.bonusPoints(), myOption.bonusPoints()) + when {
            isDraw() -> Pair(3, 3)
            isWin() -> Pair(0, 6)
            else -> Pair(6, 0)
        }
    }

    fun Round.isDraw() = opponentOption == myOption
    fun Round.isWin() = options.indexOf(myOption) == (options.indexOf(opponentOption) + 1).mod(3)

    fun part1(input: String): Int {
        val guide = input.lines().map { Round(parseOption(it[0]), parseOption(it[2])) }
        return guide
            .map(::calculateScore)
            .sumOf { it.second }
    }

    fun part2(input: String): Int {
        val guide = input.lines().map {
            val opponentOption = parseOption(it[0])
            val myOption = selectOption(opponentOption, it[2])
            Round(opponentOption, myOption)
        }

        return guide
            .map(::calculateScore)
            .sumOf { it.second }
    }
}
