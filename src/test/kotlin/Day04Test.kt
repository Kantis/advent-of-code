import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day04Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day04/sample.txt")
            Day4.part1(sample) shouldBe 4512
        }

        test("sample2") {
            val sample = loadAsText("/day04/sample.txt")
            Day4.part2(sample.lineSequence()) shouldBe 2
        }

        test("test input") {
            val sample = loadAsText("/day04/input.txt")
            Day4.part1(sample) shouldBe 1
            Day4.part2(sample.lineSequence()) shouldBe 2
        }
    }
)