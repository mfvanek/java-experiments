rootProject.name = "java-experiments"
include("console-app-example",
        "spring-boot-app-example",
        "computer-science",
        "spring-only-app-example")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("jsr305", "com.google.code.findbugs:jsr305:3.0.2")
            library("postgresql", "org.postgresql:postgresql:42.6.0")
            library("hikaricp", "com.zaxxer:HikariCP:5.0.1")
            library("logback-classic", "ch.qos.logback:logback-classic:1.4.7")
            library("slf4j-api", "org.slf4j:slf4j-api:2.0.7")
            val pgIndexHealth = version("pg-index-health", "0.9.3")
            library("pgIndexHealth-testStarter", "io.github.mfvanek", "pg-index-health-test-starter")
                    .versionRef(pgIndexHealth)
            library("pgIndexHealth-testing", "io.github.mfvanek", "pg-index-health-testing")
                    .versionRef(pgIndexHealth)
        }
    }
}
