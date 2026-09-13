plugins {
    // Apply the shared build logic from a convention plugin.
    // The shared code is located in `buildSrc/src/main/kotlin/kotlin-jvm.gradle.kts`.
    id("buildsrc.convention.kotlin-jvm")

    // Apply the Application plugin to add support for building an executable JVM application.
    application
}

dependencies {
    // Fournit kotlin.test (assertions et annotations)
    testImplementation(kotlin("test"))
    // Fournit le moteur d'exécution JUnit 5
    implementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

application {
    // Define the Fully Qualified Name for the application main class
    // (Note that Kotlin compiles `App.kt` to a class with FQN `com.example.app.AppKt`.)
    mainClass = "org.example.app.AppKt"
}

tasks.test {
    useJUnitPlatform() // Indique à Gradle d'utiliser la plateforme JUnit 5


}

kotlin {
    jvmToolchain(23)
}
