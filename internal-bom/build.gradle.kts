plugins {
    id("java-platform")
    id("maven-publish")
}

description = "Example of BOM for internal usage"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.junit:junit-bom:5.10.1"))
    api(platform("org.springframework:spring-framework-bom:6.1.2"))

    constraints {
        api("org.postgresql:postgresql:42.7.1")
        api("com.zaxxer:HikariCP:5.1.0")
        api("ch.qos.logback:logback-classic:1.4.14")
        api("org.slf4j:slf4j-api:2.0.9")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenBOM") {
            from(components["javaPlatform"])
        }
    }
}
