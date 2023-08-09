description = "Console application with PostgreSQL cluster via Testcontainers"

dependencies {
    implementation(rootProject.libs.postgresql)
    implementation(rootProject.libs.hikaricp)
    implementation(rootProject.libs.pg.index.health.testing)
    implementation(rootProject.libs.logback.classic)
    implementation(rootProject.libs.slf4j.api)

    testImplementation("org.testcontainers:postgresql")
}
