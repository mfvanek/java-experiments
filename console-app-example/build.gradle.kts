description = "Console application with PostgreSQL cluster via Testcontainers"

dependencies {
    val postgresqlVersion: String by rootProject.extra
    val slf4jVersion: String by rootProject.extra
    val logbackVersion: String by rootProject.extra
    val hikariVersion: String by rootProject.extra
    val pgihVersion: String by rootProject.extra

    implementation("org.postgresql:postgresql:${postgresqlVersion}")
    implementation("com.zaxxer:HikariCP:${hikariVersion}")
    implementation("io.github.mfvanek:pg-index-health-testing:${pgihVersion}")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")
    implementation("org.slf4j:slf4j-api:${slf4jVersion}")

    testImplementation("org.testcontainers:postgresql")
}
