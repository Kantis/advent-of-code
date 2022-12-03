import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin-conventions")
}

dependencies {
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.runner.junit5)
}
