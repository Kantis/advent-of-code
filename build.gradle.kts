import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Note: AWS supports only JDK 11 for Lambdas so far
val targetJdk = JavaVersion.VERSION_17

plugins {
    id("io.kotest.multiplatform") version "5.0.2"
    kotlin("multiplatform") version "1.6.0"
//    kotlin("jvm") version "1.6.0"
}

tasks.named<Wrapper>("wrapper") {
    gradleVersion = "7.3"
    distributionType = Wrapper.DistributionType.ALL
}

kotlin {
    sourceSets {
        val commonMain by sourceSets.getting

        val commonTest by sourceSets.getting {
            dependencies {
                implementation("io.kotest:kotest-framework-engine:_")
                implementation(Testing.kotest.property)
            }
        }
    }

    jvm()
    macosArm64("objc") {
        binaries {
            executable(buildTypes = setOf(RELEASE)) {
                debuggable = false
                entryPoint = "day12.main"
                runTask?.standardInput = System.`in`
            }
        }
    }
}

val jvmJar by tasks.existing
val jvmRuntimeClasspath by configurations.existing

tasks.register<JavaExec>("jvmRun") {
    description = "Runs this project as a JVM application"
    classpath(jvmJar, jvmRuntimeClasspath)
    mainClass.set("day12.Day12Kt")
    standardInput = System.`in`
    args = listOf("2")
}

allprojects {
    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = targetJdk.toString()
        }
    }

    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = targetJdk
            targetCompatibility = targetJdk
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
