import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class Day17Test : FunSpec(
    {

        test("total x") {
            totalX(1) shouldBe 1
            totalX(2) shouldBe 3
            totalX(3) shouldBe 6
            totalX(4) shouldBe 10
        }

        test("Only yields while in area") {
            velocity(1, 1).positions(TargetArea(0..1, 0..1)).toList() shouldBe
                    listOf(
                        PV(0, 0, 1, 1),
                        PV(1, 1, 0, 0),
                    )
        }

        test("hello") {
            velocity(2, 10)
                .positions(anyArea)
                .take(5)
                .toList() shouldBe
                    listOf(
                        PV(0, 0, 2, 10),
                        PV(2, 10, 1, 9),
                        PV(3, 19, 0, 8),
                        PV(3, 27, 0, 7),
                        PV(3, 34, 0, 6),
                    )
        }

        test("min max velocities") {
            with(TargetArea(100..200, -10..0)) {
                minXVelocity(this) shouldBe 14
                maxXVelocity(this) shouldBe 200
            }
        }

        test("sample") {
            val sample = loadAsText("/day17/sample.txt")
            Day17.part1(sample) shouldBe 1
        }

        test("sample2") {
            val sample = loadAsText("/day17/sample.txt")
            Day17.part2(sample) shouldBe 2
        }

        test("test input") {
            val sample = loadAsText("/day17/input.txt")
            Day17.part1(sample) shouldBe 1
            Day17.part2(sample) shouldBe 2
        }
    }
)