import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import info.solidsoft.gradle.pitest.PitestTask

plugins {
    id("java")
    id("jacoco")
    id("io.freefair.lombok") version "8.4" apply false
    id("org.gradle.test-retry") version "1.5.6" apply false
    id("com.github.ben-manes.versions") version "0.50.0"
    id("info.solidsoft.pitest") version "1.15.0"
}

apply(plugin = "info.solidsoft.pitest.aggregator")

description = "Experiments with Java"

allprojects {
    group = "io.github.mfvanek"
    version = "0.0.4"

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
        implementation(platform("org.testcontainers:testcontainers-bom:1.19.1"))

        testImplementation("org.assertj:assertj-core:3.24.2")
        testImplementation(platform(rootProject.libs.junit.bom))
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        val mockitoVersion = rootProject.libs.versions.mockito.get()
        testImplementation(platform("org.mockito:mockito-bom:$mockitoVersion"))
        testImplementation("org.mockito:mockito-core")

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

    val projectsWithoutTests = setOf("spring-only-app-example", "spring-boot-app-example")
    if (!projectsWithoutTests.contains(this.name)) {
        apply(plugin = "info.solidsoft.pitest")

        pitest {
            verbosity = "DEFAULT"
            junit5PluginVersion = rootProject.libs.versions.pitest.junit5Plugin.get()
            pitestVersion = rootProject.libs.versions.pitest.core.get()
            threads = 4
            outputFormats = setOf("XML", "HTML")
            timestampedReports = false
            exportLineCoverage = true

            reportAggregator {
                testStrengthThreshold = 1
                mutationThreshold = 2
                maxSurviving = 1000
            }
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
    wrapper {
        gradleVersion = "8.4"
    }
    jar {
        isEnabled = false
    }
    build {
        dependsOn("pitestReportAggregate")
    }
}
