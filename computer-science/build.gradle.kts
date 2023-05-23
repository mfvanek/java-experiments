description = "Some stuff"

dependencies {
    val slf4jVersion: String by rootProject.extra
    val logbackVersion: String by rootProject.extra

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
}
