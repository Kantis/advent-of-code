import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day12Test : FunSpec(
    {
        failfast = true

        test("sample") {
            val sample = loadAsText("/day12/sample.txt")
            Day12.part1(sample) shouldBe 10
            val bigSample = loadAsText("/day12/bigSample.txt")
            Day12.part1(bigSample) shouldBe 19

            Day12.part1("""
                fs-end
                he-DX
                fs-he
                start-DX
                pj-DX
                end-zg
                zg-sl
                zg-pj
                pj-he
                RW-he
                fs-DX
                pj-RW
                zg-RW
                start-pj
                he-WI
                zg-he
                pj-fs
                start-RW
            """.trimIndent()) shouldBe 226
        }

        test("sample2") {
            val sample = loadAsText("/day12/sample.txt")
            Day12.part2(sample) shouldBe 36
            val bigSample = loadAsText("/day12/bigSample.txt")
            Day12.part2(bigSample) shouldBe 103
        }

        test("test input") {
            val sample = loadAsText("/day12/input.txt")
            Day12.part1(sample) shouldBe 4549
            Day12.part2(sample) shouldBe 120535
        }
    }
)
