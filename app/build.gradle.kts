plugins {
    id("application")
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "6.3.1.5724"
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "hexlet.code.App"
}

dependencies {
    val picocliVersion = "4.7.7"
    val jacksonVersion = "2.15.2"

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation ("info.picocli:picocli:$picocliVersion")
    annotationProcessor ("info.picocli:picocli-codegen:$picocliVersion")
}

sonar {
    properties {
        property("sonar.projectKey", "qazhtester_qa-auto-engineer-java-project-61")
        property("sonar.organization", "qazhtester")
    }
}