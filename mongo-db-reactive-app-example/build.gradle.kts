plugins {
    id("java-experiments.java-conventions")
    id("org.springframework.boot") version "3.3.4"
    id("com.google.osdetector") version "1.7.3"
}

description = "Spring Boot reactive application with MongoDB"

dependencies {
    implementation(platform(project(":internal-spring-boot-3-bom")))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.micrometer:micrometer-registry-prometheus")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:mongodb")
    testImplementation("org.assertj:assertj-core")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // https://github.com/netty/netty/issues/11020
    if (osdetector.arch == "aarch_64") {
        testImplementation("io.netty:netty-all")
    }
}

springBoot {
    buildInfo()
}
