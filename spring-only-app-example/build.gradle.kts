plugins {
    id("java-experiments.java-conventions")
}

description = "Spring only application with embedded database"

dependencies {
    implementation(platform("org.springframework:spring-framework-bom:6.1.8"))
    implementation(platform(project(":internal-bom")))

    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework:spring-aspects")
    implementation("com.h2database:h2")
    implementation("ch.qos.logback:logback-classic")
    implementation("org.slf4j:slf4j-api")
    implementation("javax.annotation:javax.annotation-api")

    testImplementation("org.mockito:mockito-core")
}
