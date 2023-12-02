description = "Console application with PostgreSQL cluster via Testcontainers"

dependencies {
    implementation(libs.postgresql)
    implementation(libs.hikaricp)
    implementation("io.github.mfvanek:pg-index-health-testing")
    implementation(libs.logback.classic)
    implementation(libs.slf4j.api)

    testImplementation("org.testcontainers:postgresql")
}
