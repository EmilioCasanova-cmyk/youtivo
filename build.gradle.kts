plugins {
    id("com.android.application") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.diffplug.spotless") version "6.25.0" apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    // Usamos configure para evitar problemas de accessors en el Kotlin DSL del root project
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension>("spotless") {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint()
        }
        kotlinGradle {
            target("*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint()
        }
    }
}

tasks.register<Delete>("cleanLogs") {
    description = "Removes log and txt files from the root project."
    group = "cleanup"
    delete(fileTree(rootDir) {
        include("*.log")
        include("*.txt")
        exclude("build/**")
        exclude(".gradle/**")
    })
}
