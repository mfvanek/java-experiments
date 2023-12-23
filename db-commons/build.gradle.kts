plugins {
    id("java-experiments.java-conventions")
    id("java-experiments.pitest")
}

dependencies {
    implementation(libs.postgresql)
    implementation(libs.hikaricp)
    implementation(libs.slf4j.api)

    testImplementation(libs.logback.classic)
    testImplementation("org.testcontainers:postgresql")
    testImplementation("io.github.mfvanek:pg-index-health-testing")
}
