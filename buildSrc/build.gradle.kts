plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:6.5.1")
    implementation("net.ltgt.gradle:gradle-errorprone-plugin:5.1.0")
    implementation("info.solidsoft.gradle.pitest:gradle-pitest-plugin:1.19.0")
    implementation("org.gradle:test-retry-gradle-plugin:1.6.4")
    implementation("io.freefair.gradle:lombok-plugin:9.4.0")
}
