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
    api(platform(libs.spring.boot.v3.dependencies))

    constraints {
        api("org.springframework.boot:spring-boot-gradle-plugin:${libs.versions.spring.boot.v3.get()}")
    }
}
