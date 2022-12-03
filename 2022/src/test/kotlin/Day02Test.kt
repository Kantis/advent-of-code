import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day02/sample.txt")
            Day2v2.part1(sample) shouldBe 15
        }

        test("sample2") {
            val sample = loadAsText("/day02/sample.txt")
            Day2v2.part2(sample) shouldBe 12
        }

        test("test input") {
            val sample = loadAsText("/day02/input.txt")
            Day2v2.part1(sample) shouldBe 13052
            Day2v2.part2(sample) shouldBe 13693
        }
    }
)