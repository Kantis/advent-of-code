import java.util.Stack

object Day10 {
    enum class ParenSet(val starter: Char, val terminator: Char) {
        Regular('(', ')'),
        Brackets('[', ']'),
        Curly('{', '}'),
        Tags('<', '>'),
    }

    val setsByStarter: Map<Char, ParenSet> = ParenSet.values().associateBy { it.starter }
    val starters = ParenSet.values().map { it.starter }
    val terminators = ParenSet.values().map { it.terminator }

    fun p1score(char: Char) = when (char) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> error("invalid terminator")
    }

    fun String.score(stack: Stack<ParenSet> = Stack()): Int {
        if (length == 0) return 0
        return when (val curr = this[0]) {
            in starters -> substring(1).score(stack.apply { push(setsByStarter[curr]) })
            in terminators -> if (stack.peek().terminator == curr) substring(1).score(stack.apply { pop() }) else p1score(
                curr
            )
            else -> error("Invalid char: $curr")
        }
    }

    fun String.toComplete(stack: Stack<ParenSet> = Stack()): Stack<ParenSet>? {
        if (length == 0) return stack
        return when (val curr = this[0]) {
            in starters -> substring(1).toComplete(stack.apply { push(setsByStarter[curr]) })
            in terminators -> if (stack.peek().terminator == curr) substring(1).toComplete(stack.apply { pop() }) else null // Null if invalid
            else -> error("Invalid char: $curr")
        }
    }

    fun part1(lines: Sequence<String>) = lines.map { it.score() }.sum()

    fun p2score(stack: Stack<ParenSet>, curr: Long = 0): Long =
        if (stack.empty()) curr
        else p2score(stack, curr * 5 + stack.pop().ordinal + 1)

    fun part2(lines: List<String>) =
        lines.mapNotNull { it.toComplete() }
            .map { p2score(it) }
            .let {
                it.sorted()[it.count() / 2]
            }
}

fun main() {
    Day10.part1(generateSequence(::readLine))
}
