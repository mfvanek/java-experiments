description = "Spring only application with embedded database"

dependencies {
    val slf4jVersion: String by rootProject.extra
    val logbackVersion: String by rootProject.extra

    implementation(enforcedPlatform("org.springframework:spring-framework-bom:6.0.9"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework:spring-aspects")
    implementation("com.h2database:h2:2.1.214")
    implementation("ch.qos.logback:logback-classic:${logbackVersion}")
    implementation("org.slf4j:slf4j-api:${slf4jVersion}")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
}
