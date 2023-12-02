rootProject.name = "java-experiments"

include("console-app-example")
include("spring-boot-app-example")
include("computer-science")
include("spring-only-app-example")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("jsr305", "com.google.code.findbugs:jsr305:3.0.2")
            library("postgresql", "org.postgresql:postgresql:42.7.0")
            library("hikaricp", "com.zaxxer:HikariCP:5.1.0")
            library("logback-classic", "ch.qos.logback:logback-classic:1.4.14")
            library("slf4j-api", "org.slf4j:slf4j-api:2.0.9")
            val junitVersion = version("junit", "5.10.1")
            library("junit-bom", "org.junit", "junit-bom")
                .versionRef(junitVersion)
            library("pitest-dashboard-reporter", "it.mulders.stryker:pit-dashboard-reporter:0.2.1")
            version("pitest-junit5Plugin", "1.2.1")
            version("pitest-core", "1.15.3")
            version("mockito", "5.7.0")
        }
    }
}
