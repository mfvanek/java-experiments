import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("java")
    id("jacoco")
    id("org.gradle.test-retry")
    id("io.freefair.lombok")
    id("net.ltgt.errorprone")
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation(platform("org.testcontainers:testcontainers-bom:1.19.3"))
    implementation(platform("io.github.mfvanek:pg-index-health-bom:0.10.2"))

    testImplementation("org.assertj:assertj-core:3.24.2")
    versionCatalog.findLibrary("junit-bom").ifPresent {
        testImplementation(platform(it))
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation(platform("org.mockito:mockito-bom:5.7.0"))
    testImplementation("org.mockito:mockito-core")

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
