plugins {
    id("java-experiments.java-conventions")
    id("java-experiments.pitest")
}

description = "Some stuff"

dependencies {
    implementation(libs.logback.classic)
    implementation(libs.slf4j.api)
}
