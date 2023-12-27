plugins {
    id("java-experiments.java-conventions")
    id("java-experiments.pitest")
}

description = "Console application with PostgreSQL cluster via Testcontainers"

dependencies {
    implementation(platform(project(":internal-bom")))

    implementation(project(":db-commons"))
    implementation("com.zaxxer:HikariCP")
    implementation("io.github.mfvanek:pg-index-health-testing")
    implementation("ch.qos.logback:logback-classic")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.testcontainers:postgresql")
}
