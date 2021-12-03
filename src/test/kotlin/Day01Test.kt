import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day01Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day01/sample.txt")
            Day1.part1(sample.lineSequence()) shouldBe 7
        }

        test("sample2") {
            val sample = loadAsText("/day01/sample.txt")
            Day1.part2(sample.lineSequence()) shouldBe 5
        }

        test("test input") {
            val sample = loadAsText("/day01/input.txt")
            Day1.part1(sample.lineSequence()) shouldBe 1529
            Day1.part2(sample.lineSequence()) shouldBe 1567
        }
    }
)
