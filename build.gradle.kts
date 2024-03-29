val atriumVersion = "0.18.0"
val azurapiVersion = "3.1.4"
val exposedVersion = "0.38.2"
val harmonicaVersion = "1.1.26"
val imageboardVersion = "f3cb7f80b3"
val javaStringSimilarityVersion = "2.0.0"
val jdaNasVersion = "1.1.0"
val jdaVersion = "4.4.0_350"
val jdaReactorVersion = "1.2.0"
val jsonVersion = "20220320"
val konfigVersion = "1.6.10.0"
val kotlinLoggingVersion = "2.1.21"
val kotlinVersion = "1.6.21"
val kotlinxSerializationVersion = "1.3.2"
val kotlinxVersion = "1.6.1-native-mt"
val lavaplayerVersion = "1.3.78"
val logbackVersion = "1.2.11"
val mockkVersion = "1.12.3"
val postgresqlVersion = "42.3.4"
val quartzVersion = "2.3.2"
val reflectionsVersion = "0.10.2"
val sentryVersion = "5.7.3"
val spekVersion = "2.0.18"

buildscript {
    repositories {
        jcenter()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("org.jetbrains.kotlin:kotlin-script-util:1.4.30")
        classpath("com.github.KenjiOhtsuka:harmonica:1.1.26")
        classpath("com.github.cesarferreira:kotlin-pluralizer:1.0.0")
    }
}

apply(plugin = "jarmonica")

plugins {
    application
    java
    `maven-publish`
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.jmailen.kotlinter") version "3.4.0"
}

application {
    mainClassName = "com.github.sylux6.watanabot.core.Main"
}

repositories {
    mavenCentral()
    maven {
        name = "m2-dv8tion"
        url = uri("https://m2.dv8tion.net/releases")
    }
    jcenter()
    maven("https://jitpack.io")
    maven("https://dl.bintray.com/kodehawa/maven")
}

dependencies {
    // Core
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

    // Discord
    implementation("net.dv8tion:JDA:$jdaVersion") {
        exclude(module="opus-java")
    }
    implementation("com.sedmelluq:lavaplayer:$lavaplayerVersion")
    implementation("club.minnced:jda-reactor:$jdaReactorVersion")
    implementation("com.sedmelluq:jda-nas:$jdaNasVersion")

    // Database
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.quartz-scheduler:quartz:$quartzVersion")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")

    // Database migration
    implementation("com.github.KenjiOhtsuka:harmonica:$harmonicaVersion")
    implementation("org.reflections:reflections:$reflectionsVersion")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:$kotlinVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    implementation("io.sentry:sentry:$sentryVersion")
    implementation("io.sentry:sentry-logback:$sentryVersion")

    // Utils
    implementation("org.json:json:$jsonVersion")
    implementation("info.debatty:java-string-similarity:$javaStringSimilarityVersion")
    implementation("com.natpryce:konfig:$konfigVersion")

    // API
    implementation("com.github.Kodehawa:imageboard-api:$imageboardVersion")
    implementation("com.github.AzurAPI:AzurApi-Kotlin:$azurapiVersion")

    // Test
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:$atriumVersion")
}

tasks {
    test {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileJava {
        options.encoding = "UTF-8"
    }
    shadowJar {
	archiveFileName.set("watanabot.jar")
        manifest {
            attributes["Main-Class"] = "com.github.sylux6.watanabot.core.Main"
        }
        dependencies {
            exclude(dependency("com.github.KenjiOhtsuka:harmonica"))
            exclude(dependency("org.reflections:reflections"))
            exclude(dependency("org.jetbrains.kotlin:kotlin-script-runtime"))
        }
       	// Exclude dependencies if there are ClassNotFoundError with them
        minimize {
	        exclude(dependency("com.sedmelluq:lavaplayer"))
            exclude(dependency("ch.qos.logback:logback-classic"))
	        exclude(dependency("io.github.microutils:kotlin-logging"))
            exclude(dependency("org.quartz-scheduler:quartz"))
            exclude(dependency(("org.jetbrains.exposed:exposed-dao")))
            exclude(dependency(("org.jetbrains.exposed:exposed-jdbc")))
            exclude(dependency("org.postgresql:postgresql"))
            exclude(dependency("io.sentry:sentry-logback"))
        }
    }
    create("cleanLogs", Delete::class) {
        group = "log"
        delete = setOf(
            "yousolog-error.log", "yousolog-info.log"
        )
    }
}
