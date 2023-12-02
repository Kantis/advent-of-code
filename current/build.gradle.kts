plugins {
    id("kotlin-conventions")
}

dependencies {
    implementation(projects.lib)
    testImplementation(libs.kotest.property)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.datatest)
}