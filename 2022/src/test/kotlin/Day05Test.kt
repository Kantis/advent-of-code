import Day5.Move
import Day5.parseMoves
import Day5.parseStacks
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class Day05Test : FunSpec(
    {
        test("Parse stacks") {
            parseStacks(loadAsText("/day05/sample.txt").split("\n\n")[0]) shouldBe
                    listOf(
                        listOf('Z', 'N'),
                        listOf('M', 'C', 'D'),
                        listOf('P')
                    )
        }

        test("parse moves") {
            parseMoves(loadAsText("/day05/sample.txt").split("\n\n")[1]).shouldContainExactly(
                Move(1, 0, 1),
                Move(0, 2, 3),
                Move(1, 0, 2),
                Move(0, 1, 1),
            )
        }

        test("sample") {
            val sample = loadAsText("/day05/sample.txt")
            Day5.part1(sample) shouldBe "CMZ"
        }

        test("sample2") {
            val sample = loadAsText("/day05/sample.txt")
            Day5.part2(sample) shouldBe "MCD"
        }

        test("test input") {
            val sample = loadAsText("/day05/input.txt")
            Day5.part1(sample) shouldBe "QGTHFZBHV"
            Day5.part2(sample) shouldBe "MGDMPSZTM"
        }
    }
)