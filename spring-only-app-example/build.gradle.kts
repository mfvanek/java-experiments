description = "Spring only application with embedded database"

dependencies {
    implementation(platform("org.springframework:spring-framework-bom:6.1.1"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework:spring-aspects")
    implementation("com.h2database:h2:2.2.224")
    implementation(rootProject.libs.logback.classic)
    implementation(rootProject.libs.slf4j.api)
    implementation("javax.annotation:javax.annotation-api:1.3.2")
}
