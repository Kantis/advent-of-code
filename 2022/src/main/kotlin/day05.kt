typealias Stacks = List<List<Char>>

object Day5 {
    data class Move(val from: Int, val to: Int, val amount: Int)

    fun parseStacks(input: String): Stacks {
        val lines = input.lines()
        val crateLines = lines.dropLast(1)
        val numberOfStacks = lines.last().trim().split("\\s+".toRegex()).last().toInt()
        return (0 until numberOfStacks)
            .map { i ->
                val index = 1 + 4 * i
                crateLines
                    .mapNotNull { if (index in it.indices) it[index] else null }
                    .filterNot { it == ' ' }
                    .reversed()
            }
    }

    val moveRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
    fun parseMoves(input: String): List<Move> = input
        .lines()
        .mapNotNull(moveRegex::matchEntire)
        .map(MatchResult::groupValues)
        .map {
            it.drop(1).take(3).map(String::toInt).let { (amount, from, to) ->
                // Make it zero-indexed
                Move(from - 1, to - 1, amount)
            }
        }

    fun Stacks.applyMove(move: Move, movedStackFn: List<Char>.() -> List<Char>): Stacks =
        mapIndexed { i, stack ->
            when (i) {
                move.from -> stack.dropLast(move.amount)
                move.to -> stack +
                        this[move.from]
                            .takeLast(move.amount)
                            .run(movedStackFn)

                else -> stack
            }
        }

    fun parseInput(input: String): Pair<Stacks, List<Move>> =
        input
            .split("\n\n")
            .let { (stackInput, moveInput) ->
                parseStacks(stackInput) to parseMoves(moveInput)
            }

    fun part1(input: String) = parseInput(input).let { (stacks, moves) ->
        moves.fold(stacks) { s, move -> s.applyMove(move, Iterable<Char>::reversed) }
            .joinToString(separator = "") { it.last().toString() }
    }

    fun part2(input: String) = parseInput(input).let { (stacks, moves) ->
        moves.fold(stacks) { s, move -> s.applyMove(move, ::identity) }
            .joinToString(separator = "") { it.last().toString() }
    }
}
