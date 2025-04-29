plugins {
    id("java-experiments.java-conventions")
	id("org.springframework.boot") version "3.4.5"
    id("com.google.osdetector") version "1.7.3"
}

description = "Spring Boot application with PostgreSQL cluster via Testcontainers"

dependencies {
    implementation(platform(project(":internal-spring-boot-3-bom")))

    implementation(project(":db-commons"))
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.github.mfvanek:pg-index-health-testing")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.micrometer:micrometer-registry-prometheus")
    runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.threeten:threeten-extra")
    testImplementation("io.github.mfvanek:pg-index-health-test-starter")

    // https://github.com/netty/netty/issues/11020
    if (osdetector.arch == "aarch_64") {
        testImplementation("io.netty:netty-all")
    }
}

springBoot {
    buildInfo()
}

tasks.bootRun {
    if (project.hasProperty("jvmArgs")) {
        val jvmArgsFromCommandLine = project.properties["jvmArgs"].toString().split("\\s".toRegex())
        jvmArgs = jvmArgsFromCommandLine
    }
}
