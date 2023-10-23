plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
    id("com.google.osdetector") version "1.7.3"
}

description = "Spring Boot application with PostgreSQL cluster via Testcontainers"

dependencies {
    implementation(project(":console-app-example"))
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation(rootProject.libs.pg.index.health.testing)
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly(rootProject.libs.postgresql)

	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.threeten:threeten-extra:1.7.2")
    testImplementation(rootProject.libs.pg.index.health.testStarter)

    // https://github.com/netty/netty/issues/11020
    if (osdetector.arch == "aarch_64") {
        testImplementation("io.netty:netty-all:4.1.100.Final")
    }
}

dependencyManagement {
    imports {
        val junitVersion = rootProject.libs.versions.junit.get()
        mavenBom("org.junit:junit-bom:$junitVersion")
        val mockitoVersion = rootProject.libs.versions.mockito.get()
        mavenBom("org.mockito:mockito-bom:$mockitoVersion")
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
