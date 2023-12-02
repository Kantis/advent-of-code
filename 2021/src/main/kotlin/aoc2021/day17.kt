package aoc2021

import kotlin.math.max
import kotlin.math.min

data class PV(
    val x: Int,
    val y: Int,
    val vx: Int,
    val vy: Int,
)

data class TargetArea(val xRange: IntRange, val yRange: IntRange)

val anyArea = TargetArea(Int.MIN_VALUE..Int.MAX_VALUE, Int.MIN_VALUE..Int.MAX_VALUE)

fun decay(vx: Int) =
    if (vx > 0) max(vx - 1, 0)
    else min(vx + 1, 0)

fun PV.positions(targetArea: TargetArea) = generateSequence(this) { (x, y, vx, vy) ->
    if (x < targetArea.xRange.last || y < targetArea.yRange.last) {
        PV(x + vx, y + vy, decay(vx), vy - 1)
    } else null
}

fun totalX(velocity: Int) = velocity * (1 + velocity) / 2
fun totalY(velocity: Int) = velocity * (1 + velocity) / 2

// max x = sum
fun maxXVelocity(targetArea: TargetArea) = targetArea.xRange.last
fun minXVelocity(targetArea: TargetArea): Int {
    val mustReach = targetArea.xRange.first
    return (0..targetArea.xRange.last).first { totalX(it) >= mustReach }
}

fun maxYVelocity(targetArea: TargetArea): Int {
    val mustReach = targetArea.xRange.last
    return (0..targetArea.yRange.last).first { totalY(it) <= mustReach }
}

fun velocity(vx: Int, vy: Int) = PV(0, 0, vx, vy)

val rangePattern = """(-?\d+)\.\.(-?\d+)""".toRegex()
fun parseTargetArea(input: String) = input.split(",").map {
    rangePattern.find(it)!!.groups.let {
        it[1]!!.value.toInt()..it[2]!!.value.toInt()
    }
}.let { (a, b) -> TargetArea(a, b) }

object Day17 {
    fun part1(input: String): Int {
        val target = parseTargetArea(input)
        val minX = minXVelocity(target)
        println(minX)
        println(maxYVelocity(target))
        return 1
    }

    fun part2(input: String): Int {
        return 2
    }
}
