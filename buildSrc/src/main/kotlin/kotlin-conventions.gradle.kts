import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import org.gradle.api.JavaVersion
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType

val targetJdk = JavaVersion.VERSION_17

plugins {
    kotlin("jvm")
}

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
