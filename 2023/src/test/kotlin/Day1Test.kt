import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day01/sample.txt")
            Day1.part1(sample) shouldBe 24000
        }

        test("sample2") {
            val sample = loadAsText("/day01/sample.txt")
            Day1.part2(sample) shouldBe 45000
        }

        test("test input") {
            val sample = loadAsText("/day01/input.txt")
            Day1.part1(sample) shouldBe 73211
            Day1.part2(sample) shouldBe 213958
        }
    }
)
