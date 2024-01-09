plugins {
    id("java-platform")
    id("java-experiments.bom-publish")
}

description = "Example of BOM for internal usage"

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.junit:junit-bom:5.10.1"))
    api(platform("org.testcontainers:testcontainers-bom:1.19.3"))
    api(platform("io.github.mfvanek:pg-index-health-bom:0.10.2"))
    api(platform("org.mockito:mockito-bom:5.8.0"))

    constraints {
        api("com.google.code.findbugs:jsr305:3.0.2")
        api("org.postgresql:postgresql:42.7.1")
        api("com.zaxxer:HikariCP:5.1.0")
        api("ch.qos.logback:logback-classic:1.4.14")
        api("org.slf4j:slf4j-api:2.0.10")
        api("org.assertj:assertj-core:3.25.1")
        api("com.h2database:h2:2.2.224")
        api("javax.annotation:javax.annotation-api:1.3.2")
        api("org.threeten:threeten-extra:1.7.2")
        api("io.netty:netty-all:4.1.104.Final")
    }
}
