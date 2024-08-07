plugins {
    id("java-platform")
    id("java-experiments.bom-publish")
}

description = "Example of BOM for internal usage"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.junit:junit-bom:5.10.3"))
    api(platform("org.testcontainers:testcontainers-bom:1.20.1"))
    api(platform("io.github.mfvanek:pg-index-health-bom:0.13.0"))
    api(platform("org.mockito:mockito-bom:5.12.0"))
    api(platform("org.assertj:assertj-bom:3.26.3"))

    constraints {
        api("com.google.code.findbugs:jsr305:3.0.2")
        api("org.postgresql:postgresql:42.7.3")
        api("com.zaxxer:HikariCP:5.1.0")
        api("ch.qos.logback:logback-classic:1.5.6")
        api("org.slf4j:slf4j-api:2.0.14")
        api("com.h2database:h2:2.3.230")
        api("javax.annotation:javax.annotation-api:1.3.2")
        api("org.threeten:threeten-extra:1.8.0")
        api("io.netty:netty-all:4.1.112.Final")
    }
}
