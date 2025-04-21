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
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    errorprone("com.google.errorprone:error_prone_core:2.38.0")
    errorprone("jp.skypencil.errorprone.slf4j:errorprone-slf4j:0.1.28")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

jacoco {
    toolVersion = "0.8.12"
}

tasks {
    withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-parameters")
        options.compilerArgs.add("--should-stop=ifError=FLOW")
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
