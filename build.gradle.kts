import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.53.0"
    id("info.solidsoft.pitest")
    id("jacoco-report-aggregation")
}

apply(plugin = "info.solidsoft.pitest.aggregator")

description = "Experiments with Java"

allprojects {
    group = "io.github.mfvanek"
    version = "0.3.1"

    repositories {
        mavenLocal()
        mavenCentral()
    }
}

dependencies {
    subprojects.forEach {
        if (!it.name.startsWith("spring-boot-") &&
            !it.name.endsWith("-bom")) {
            jacocoAggregation(it)
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

tasks {
    wrapper {
        gradleVersion = "9.1.0"
    }

    jar {
        // To avoid creation of jar in the root build folder
        isEnabled = false
    }

    check {
        dependsOn(named<JacocoReport>("testCodeCoverageReport"))
    }

    build {
        dependsOn("pitestReportAggregate")
    }
}
