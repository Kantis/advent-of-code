import aoc2021.Day10
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day10Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day10/sample.txt")
            Day10.part1(sample.lineSequence()) shouldBe 26397
        }

        test("sample2") {
            val sample = loadAsText("/day10/sample.txt")
            Day10.part2(sample.lines()) shouldBe 288957L
        }

        test("test input") {
            val sample = loadAsText("/day10/input.txt")
            Day10.part1(sample.lineSequence()) shouldBe 469755
            Day10.part2(sample.lines()) shouldBe 2
        }
    }
)
