import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("java")
    id("jacoco")
    id("io.freefair.lombok") version "8.2.2" apply false
    id("org.gradle.test-retry") version "1.5.4" apply false
    id("com.github.ben-manes.versions") version "0.47.0"
}

description = "Experiments with Java"

allprojects {
    group = "io.github.mfvanek"
    version = "0.0.2-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "org.gradle.test-retry")

    dependencies {
        implementation(rootProject.libs.jsr305)
        implementation(platform("org.testcontainers:testcontainers-bom:1.19.0"))

        testImplementation("org.assertj:assertj-core:3.24.2")
        testImplementation(platform(rootProject.libs.junit.bom))
        testImplementation("org.junit.jupiter:junit-jupiter-api")

        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    jacoco {
        toolVersion = "0.8.10"
    }

    tasks {
        test {
            useJUnitPlatform()
            retry {
                maxRetries.set(1)
                maxFailures.set(3)
                failOnPassedAfterRetry.set(false)
            }
            finalizedBy(jacocoTestReport)
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    checkForGradleUpdate = true
    gradleReleaseChannel = "current"
    checkConstraints = true
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

// To avoid creation of build folder in the root
tasks {
    jar {
        isEnabled = false
    }
    build {
        isEnabled = false
    }
}
