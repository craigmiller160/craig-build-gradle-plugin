import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.streams.asSequence

val slf4jVersion = "1.7.36"
val gradleToolingApiVersion = "7.4.2"

plugins {
    kotlin("jvm") version "1.6.20"
    id("com.diffplug.spotless") version "6.6.1"
}

group = "io.craigmiller160"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases")
    }
}

configure<SpotlessExtension> {
    kotlin {
        ktfmt()
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(gradleApi())
    implementation("org.gradle:gradle-tooling-api:$gradleToolingApiVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

task("installGitHooks") {
    val hooksDevDir = Paths.get(rootProject.rootDir.absolutePath, "hooks")
    val hooksGitDir = Paths.get(rootProject.rootDir.absolutePath, ".git", "hooks")
    runCatching {
        Files.list(hooksDevDir)
            .forEach { hookPath ->
                val hookGitPath = hooksGitDir.resolve(hookPath.fileName)
                Files.copy(hookPath, hookGitPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES)
            }
    }
}

tasks.getByPath("compileKotlin").dependsOn("installGitHooks")