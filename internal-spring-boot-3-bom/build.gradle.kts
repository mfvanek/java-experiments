plugins {
    id("java-platform")
    id("java-experiments.bom-publish")
}

description = "Spring Boot 3 cumulative BOM"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform(project(":internal-bom")))
    val spring3Version = "3.3.4"
    api(platform("org.springframework.boot:spring-boot-dependencies:$spring3Version"))

    constraints {
        api("org.springframework.boot:spring-boot-gradle-plugin:$spring3Version")
    }
}
