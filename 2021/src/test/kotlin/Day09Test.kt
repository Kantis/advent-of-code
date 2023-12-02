import aoc2021.Day9
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day09Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day09/sample.txt")
            Day9.part1(sample.lines()) shouldBe 15
        }

        test("sample2") {
            val sample = loadAsText("/day09/sample.txt")
            Day9.part2(sample.lines()) shouldBe 1134
        }

        test("test input") {
            val sample = loadAsText("/day09/input.txt")
            Day9.part1(sample.lines()) shouldBe 535
            Day9.part2(sample.lines()) shouldBe 2
        }
    }
)
