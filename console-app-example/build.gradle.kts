plugins {
    id("java-experiments.java-conventions")
    id("java-experiments.pitest")
}

description = "Console application with PostgreSQL cluster via Testcontainers"

dependencies {
    implementation(project(":db-commons"))
    implementation(libs.hikaricp)
    implementation("io.github.mfvanek:pg-index-health-testing")
    implementation(libs.logback.classic)

    runtimeOnly(libs.postgresql)

    testImplementation("org.testcontainers:postgresql")
}
