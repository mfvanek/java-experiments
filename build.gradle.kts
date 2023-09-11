import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import info.solidsoft.gradle.pitest.PitestTask

plugins {
    id("java")
    id("jacoco")
    id("io.freefair.lombok") version "8.3" apply false
    id("org.gradle.test-retry") version "1.5.5" apply false
    id("com.github.ben-manes.versions") version "0.47.0"
    id("info.solidsoft.pitest") version "1.9.11"
}

apply(plugin = "info.solidsoft.pitest.aggregator")

description = "Experiments with Java"

allprojects {
    group = "io.github.mfvanek"
    version = "0.0.3"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

val pitestVerbosity = "DEFAULT"
val pitestThreads = 4
val pitestOutputFormats = setOf("XML", "HTML")

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
        testImplementation(platform("org.mockito:mockito-bom:5.5.0"))
        testImplementation("org.mockito:mockito-core")

        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
            because("required for pitest")
        }
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

    val projectsWithoutTests = setOf("spring-only-app-example", "spring-boot-app-example")
    if (!projectsWithoutTests.contains(this.name)) {
        apply(plugin = "info.solidsoft.pitest")

        pitest {
            verbosity.set(pitestVerbosity)
            junit5PluginVersion.set(rootProject.libs.versions.pitest.junit5Plugin.get())
            pitestVersion.set(rootProject.libs.versions.pitest.core.get())
            threads.set(pitestThreads)
            outputFormats.set(pitestOutputFormats)
            timestampedReports.set(false)
            exportLineCoverage.set(true)
        }
        tasks.withType<PitestTask>().configureEach {
            mustRunAfter(tasks.test)
        }
        tasks.build {
            dependsOn("pitest")
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
        dependsOn("pitestReportAggregate")
    }
}

pitest {
    verbosity.set(pitestVerbosity)
    junit5PluginVersion.set(rootProject.libs.versions.pitest.junit5Plugin.get())
    pitestVersion.set(rootProject.libs.versions.pitest.core.get())
    threads.set(pitestThreads)
    outputFormats.set(pitestOutputFormats)
    timestampedReports.set(false)
    exportLineCoverage.set(true)

    reportAggregator {
        testStrengthThreshold.set(1)
        mutationThreshold.set(2)
        maxSurviving.set(1000)
    }
}
