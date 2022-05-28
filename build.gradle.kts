import com.diffplug.gradle.spotless.SpotlessExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val slf4jVersion = "1.7.36"
val gradleToolingApiVersion = "7.4.2"

plugins {
    kotlin("jvm") version "1.6.20"
    id("com.diffplug.spotless") version "6.6.1"
    `maven-publish`
}

group = "io.craigmiller160"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = rootProject.name
            version = project.version.toString()

            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            val repo = if (project.version.toString().endsWith("-SNAPSHOT")) "maven-snapshots" else "maven-releases"
            url = uri("https://craigmiller160.ddns.net:30003/repository/$repo")
            credentials {
                username = System.getenv("NEXUS_USER")
                password = System.getenv("NEXUS_PASSWORD")
            }
        }
    }
}

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
    compileOnly(gradleApi())
    implementation("org.gradle:gradle-tooling-api:$gradleToolingApiVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}