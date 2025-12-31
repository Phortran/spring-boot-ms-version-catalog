plugins {
    `version-catalog`
    `maven-publish`
}

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}

repositories {
    mavenCentral()
}

publishing {
    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/Phortran/spring-boot-ms-version-catalog")
                credentials {
                    username = System.getenv("GITHUB_ACTOR") ?: findProperty("gpr.user") as String?
                    password = System.getenv("GITHUB_TOKEN") ?: findProperty("gpr.key") as String?
                }
            }
        }
    }
    publications {
        create<MavenPublication>("catalog") {
            from(components["versionCatalog"])
            groupId = "$group"
            artifactId = "spring-boot-ms-version-catalog"
            version = "1.0.6"
        }
    }
}