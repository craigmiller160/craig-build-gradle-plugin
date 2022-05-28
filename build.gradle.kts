import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val slf4jVersion = "1.7.36"
val gradleToolingApiVersion = "7.4.2"

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "io.craigmiller160"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases")
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