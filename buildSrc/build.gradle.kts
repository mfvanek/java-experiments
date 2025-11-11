plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:6.4.2")
    implementation("net.ltgt.gradle:gradle-errorprone-plugin:4.3.0")
    implementation("info.solidsoft.gradle.pitest:gradle-pitest-plugin:1.19.0-rc.2")
    implementation("org.gradle:test-retry-gradle-plugin:1.6.4")
    implementation("io.freefair.gradle:lombok-plugin:9.1.0")
}
