plugins {
    id("java-platform")
    id("java-experiments.bom-publish")
}

description = "Example of BOM for internal usage"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.junit:junit-bom:6.0.1"))
    api(platform("org.testcontainers:testcontainers-bom:1.21.3"))
    api(platform("io.github.mfvanek:pg-index-health-bom:0.30.2"))
    api(platform("org.mockito:mockito-bom:5.21.0"))
    api(platform("org.assertj:assertj-bom:3.27.6"))

    constraints {
        api("com.google.code.findbugs:jsr305:3.0.2")
        api("org.postgresql:postgresql:42.7.8")
        api("com.zaxxer:HikariCP:7.0.2")
        api("ch.qos.logback:logback-classic:1.5.22")
        api("org.slf4j:slf4j-api:2.0.17")
        api("com.h2database:h2:2.4.240")
        api("javax.annotation:javax.annotation-api:1.3.2")
        api("org.threeten:threeten-extra:1.8.0")
        api("io.netty:netty-all:4.2.9.Final")
    }
}
