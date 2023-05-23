plugins {
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
    id("com.google.osdetector") version "1.7.3"
}

description = "Spring Boot application with PostgreSQL cluster via Testcontainers"

dependencies {
    val pgihVersion: String by rootProject.extra

    implementation(project(":console-app-example"))
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.github.mfvanek:pg-index-health-testing:$pgihVersion")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.threeten:threeten-extra:1.7.2")
    testImplementation("io.github.mfvanek:pg-index-health-test-starter:$pgihVersion")

    // https://github.com/netty/netty/issues/11020
    if (osdetector.arch == "aarch_64") {
        testImplementation("io.netty:netty-all:4.1.92.Final")
    }
}

springBoot {
    buildInfo()
}
