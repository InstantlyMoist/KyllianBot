plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

val testFolder = File(project.projectDir, "test").apply {if (!exists()) mkdirs()}

group = "nl.kyllian"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.5")
    implementation("ch.qos.logback:logback-classic:1.2.8")
    implementation("com.google.code.gson:gson:2.10.1")
}


tasks {
    shadowJar {
        archiveClassifier.set("")
        manifest {
            attributes(
                "Main-Class" to "nl.kyllian.DiscordBotKt"
            )
        }
        val file = archiveFile.get().asFile
        doLast {
            copy {
                from(file)
                into(testFolder)
                rename { "DiscordBot.jar" }
            }
        }
    }
}
