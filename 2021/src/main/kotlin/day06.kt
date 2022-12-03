typealias FishBuckets = Map<Int, Long>

object Day6 {

    fun FishBuckets.step() =
        mapOf(
            0 to (this[1] ?: 0),
            1 to (this[2] ?: 0),
            2 to (this[3] ?: 0),
            3 to (this[4] ?: 0),
            4 to (this[5] ?: 0),
            5 to (this[6] ?: 0),
            6 to ((this[0] ?: 0) + (this[7] ?: 0)),
            7 to (this[8] ?: 0),
            8 to (this[0] ?: 0)
        )

    fun parseInitialState(fishes: String) =
        fishes.split(",").groupBy { it.toInt() }.mapValues { (key, values) -> values.count().toLong() }

    fun FishBuckets.iterateAndSum(days: Int): Long {
        var state = this
        repeat(days) { state = state.step() }
        return state.map { (_, count) -> count }.sum()
    }

    fun part1(lines: String): Long = parseInitialState(lines).iterateAndSum(80)
    fun part2(lines: String): Long = parseInitialState(lines).iterateAndSum(256)
}
