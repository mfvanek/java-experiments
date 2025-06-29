plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:6.1.13")
    implementation("net.ltgt.gradle:gradle-errorprone-plugin:4.2.0")
    implementation("info.solidsoft.gradle.pitest:gradle-pitest-plugin:1.15.0")
    implementation("org.gradle:test-retry-gradle-plugin:1.6.2")
    implementation("io.freefair.gradle:lombok-plugin:8.14")
}
