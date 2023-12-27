plugins {
    id("java-experiments.java-conventions")
    id("java-experiments.pitest")
}

dependencies {
    implementation(platform(project(":internal-bom")))

    implementation("org.postgresql:postgresql")
    implementation("com.zaxxer:HikariCP")
    implementation("org.slf4j:slf4j-api")

    testImplementation("ch.qos.logback:logback-classic")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("io.github.mfvanek:pg-index-health-testing")
}
