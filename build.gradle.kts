plugins {
    id("java")
    id("jacoco")
    id("io.freefair.lombok") version "8.0.1" apply false
    id("org.gradle.test-retry") version "1.5.3" apply false
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
        implementation(platform("org.testcontainers:testcontainers-bom:1.18.3"))

        testImplementation("org.assertj:assertj-core:3.24.2")
        testImplementation(platform("org.junit:junit-bom:5.9.3"))
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
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

// To avoid creation of build folder in the root
tasks {
    jar {
        isEnabled = false
    }
    build {
        isEnabled = false
    }
}
