import Day2.Option.PAPER
import Day2.Option.ROCK
import Day2.Option.SCISSORS

object Day2v2 {

    fun score1(round: String) = when (round) {
        "A X" -> 4
        "A Y" -> 8
        "A Z" -> 3
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 7
        "C Y" -> 2
        "C Z" -> 6
        else -> error("Unexpected round `$round`")
    }

    fun score2(round: String) = when (round) {
        "A X" -> 3
        "A Y" -> 4
        "A Z" -> 8
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 2
        "C Y" -> 6
        "C Z" -> 7
        else -> error("Unexpected round `$round`")
    }

    fun part1(input: String): Int {
        return input.lines().map(::score1).sum()
    }

    fun part2(input: String): Int {
        return input.lines().map(::score2).sum()
    }
}
