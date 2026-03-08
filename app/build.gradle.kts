plugins {
    application
    checkstyle
    jacoco
    id("org.sonarqube") version "7.1.0.6387"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

apply(plugin = "com.github.ben-manes.versions")

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.github.ben-manes:gradle-versions-plugin:0.53.0")
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.21.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.21.1")
    implementation("info.picocli:picocli:4.7.7")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

application {
    mainClass = "hexlet.code.App"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

sonar {
    properties {
        property("sonar.projectKey", "GypsyJR777_java-project-71")
        property("sonar.organization", "gypsyjr777")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            layout.buildDirectory.file("reports/jacoco/test/jacocoTestReport.xml").get().asFile.path,
        )
    }
}
