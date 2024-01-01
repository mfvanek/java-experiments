plugins {
    id("java-platform")
    id("java-experiments.bom-publish")
}

description = "Spring Boot 2 cumulative BOM"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform(project(":internal-bom")))
    api(platform("org.springdoc:springdoc-openapi:1.7.0"))
    api(platform("org.springframework.boot:spring-boot-dependencies:2.7.18"))
    api(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.9"))
    api(platform("org.springframework.cloud:spring-cloud-sleuth-otel-dependencies:1.1.4"))
    api(platform("org.springframework.security:spring-security-bom:6.2.1"))

    constraints {
        api("ch.qos.logback:logback-classic") {
            version {
                strictly("1.2.13")
                because("java.lang.NoClassDefFoundError: org/slf4j/impl/StaticLoggerBinder")
            }
        }
        api("org.slf4j:slf4j-api") {
            version {
                strictly("1.7.36")
                because("SLF4J: No SLF4J providers were found.")
            }
        }
        api("org.yaml:snakeyaml") {
            version {
                strictly("1.33")
                because("org.yaml:snakeyaml:1.30 -> 2.0 (c)")
            }
        }
    }
}
