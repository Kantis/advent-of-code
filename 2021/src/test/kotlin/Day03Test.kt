import aoc2021.Day3
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day03Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day03/sample.txt")
            Day3.part1(sample.lineSequence().toList()) shouldBe 198
        }

        test("sample2") {
            val sample = loadAsText("/day03/sample.txt")
            Day3.part2(sample.lineSequence().toList()) shouldBe 230
        }

        test("test input") {
            val sample = loadAsText("/day03/input.txt")
            Day3.part1(sample.lineSequence().toList()) shouldBe 4006064
            Day3.part2(sample.lineSequence().toList()) shouldBe 5941884
        }
    }
)
