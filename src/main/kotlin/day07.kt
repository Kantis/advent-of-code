import kotlin.math.abs

typealias CrabBuckets = Map<Int, Int>

object Day7 {

    // 1 3 6 10 15
    // 1 2 3  4  5

    fun CrabBuckets.fuelCost(from: Int, to: Int) = abs((this[from] ?: 0) * (from - to))
    fun CrabBuckets.fuelCost2(from: Int, to: Int) = (this[from] ?: 0) * abs(from - to).let { it * (it + 1) / 2 }

    data class BucketCost(val position: Int, val totalCost: Int)

    fun part1(lines: String): Int {
        val buckets = lines.split(",").map { it.toInt() }.groupBy { it }.mapValues { (_, values) -> values.count() }

        val minPos = buckets.keys.minOf { it }
        val maxPos = buckets.keys.maxOf { it }

        val costs = (minPos..maxPos).map { to ->
            BucketCost(to, buckets.keys.sumOf { from -> buckets.fuelCost(from, to) })
        }

        return costs.minByOrNull { it.totalCost }?.totalCost ?: error("No position found")
    }

    fun part2(lines: String): Int {
        val buckets = lines.split(",").map { it.toInt() }.groupBy { it }.mapValues { (_, values) -> values.count() }

        val minPos = buckets.keys.minOf { it }
        val maxPos = buckets.keys.maxOf { it }

        val costs = (minPos..maxPos).map { to ->
            BucketCost(to, buckets.keys.sumOf { from -> buckets.fuelCost2(from, to) })
        }

        return costs.minByOrNull { it.totalCost }?.totalCost ?: error("No position found")
    }
}

fun main() {
}
