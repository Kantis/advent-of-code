import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Test : FunSpec(
    {
        test("sample") {
            val sample = loadAsText("/day02/sample.txt")
            Day2.part1(sample.lineSequence()) shouldBe 900
        }

        test("test input") {
            val sample = loadAsText("/day02/input.txt")
            Day2.part1(sample.lineSequence()) shouldBe 1856459736
        }
    }
)
