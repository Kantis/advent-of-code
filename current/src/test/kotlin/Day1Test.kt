import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import se.codeboss.aoc2023.Day1

class Day1Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day01/sample.txt")
            Day1.part1(sample) shouldBe 142
        }

        test("sample2") {
            val sample = loadAsText("/day01/sample2.txt")
            Day1.part2(sample) shouldBe 281
        }

        test("test input") {
            val puzzleInput = loadAsText("/day01/input.txt")
            Day1.part1(puzzleInput) shouldBe 53974
            Day1.part2(puzzleInput) shouldBe 52840
        }
    },
)
