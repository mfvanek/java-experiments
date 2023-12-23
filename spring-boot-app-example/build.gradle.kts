plugins {
    id("java-experiments.java-conventions")
	id("org.springframework.boot") version "3.1.6"
	id("io.spring.dependency-management") version "1.1.4"
    id("com.google.osdetector") version "1.7.3"
}

description = "Spring Boot application with PostgreSQL cluster via Testcontainers"

dependencies {
    implementation(project(":db-commons"))
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.github.mfvanek:pg-index-health-testing")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly(libs.postgresql)

	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.threeten:threeten-extra:1.7.2")
    testImplementation("io.github.mfvanek:pg-index-health-test-starter")

    // https://github.com/netty/netty/issues/11020
    if (osdetector.arch == "aarch_64") {
        testImplementation("io.netty:netty-all:4.1.104.Final")
    }
}

dependencyManagement {
    imports {
        val junitVersion = libs.versions.junit.get()
        mavenBom("org.junit:junit-bom:$junitVersion")
        mavenBom("org.mockito:mockito-bom:5.7.0")
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
