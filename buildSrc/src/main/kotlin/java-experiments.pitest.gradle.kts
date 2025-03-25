import info.solidsoft.gradle.pitest.PitestTask

plugins {
    id("java")
    id("info.solidsoft.pitest")
}

dependencies {
    testImplementation("org.junit.platform:junit-platform-launcher")
}

pitest {
    verbosity = "DEFAULT"
    junit5PluginVersion = "1.2.1"
    pitestVersion = "1.19.0"
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

tasks {
    withType<PitestTask>().configureEach {
        mustRunAfter(test)
    }

    build {
        dependsOn("pitest")
    }
}
