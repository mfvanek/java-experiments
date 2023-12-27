plugins {
    id("java-experiments.java-conventions")
    id("org.springframework.boot") version "2.7.18"
    id("com.google.osdetector") version "1.7.3"
}

description = "Spring Boot 2 application with Swagger"

dependencies {
    implementation(platform(project(":internal-spring-boot-2-bom")))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")

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
