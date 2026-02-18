plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    id("com.diffplug.spotless") version "6.25.0"
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
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
