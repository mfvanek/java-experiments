plugins {
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("mavenBOM") {
            from(components["javaPlatform"])
        }
    }
}
