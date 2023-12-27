import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("java")
    id("jacoco")
    id("org.gradle.test-retry")
    id("io.freefair.lombok")
    id("net.ltgt.errorprone")
}

dependencies {
    implementation("com.google.code.findbugs:jsr305")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    errorprone("com.google.errorprone:error_prone_core:2.24.0")
    errorprone("jp.skypencil.errorprone.slf4j:errorprone-slf4j:0.1.21")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

jacoco {
    toolVersion = "0.8.11"
}

tasks {
    withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-parameters")
        options.errorprone {
            disableWarningsInGeneratedCode.set(true)
            disable("Slf4jLoggerShouldBeNonStatic")
        }
    }

    test {
        useJUnitPlatform()
        retry {
            maxRetries.set(1)
            maxFailures.set(3)
            failOnPassedAfterRetry.set(false)
        }
        finalizedBy(jacocoTestReport, jacocoTestCoverageVerification)
    }

    jacocoTestCoverageVerification {
        dependsOn(jacocoTestReport)
        violationRules {
            rule {
                limit {
                    counter = "INSTRUCTION"
                    value = "COVEREDRATIO"
                    minimum = "0.0".toBigDecimal()
                }
            }
            rule {
                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.0".toBigDecimal()
                }
            }
        }
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    check {
        dependsOn(jacocoTestCoverageVerification)
    }
}
