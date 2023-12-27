plugins {
    id("java-experiments.java-conventions")
    id("java-experiments.pitest")
}

description = "Some stuff"

dependencies {
    implementation(platform(project(":internal-bom")))

    implementation("ch.qos.logback:logback-classic")
    implementation("org.slf4j:slf4j-api")
}
