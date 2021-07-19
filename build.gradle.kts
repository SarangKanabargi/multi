import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("pl.allegro.tech.build.axion-release") version "1.13.3"
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
}


allprojects {
    apply(plugin = "pl.allegro.tech.build.axion-release")

    group = "com.example"
    version = scmVersion.version

    repositories {
        mavenCentral()
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java")


    java.sourceCompatibility = JavaVersion.VERSION_11

    dependencies {
        implementation("pl.allegro.tech.build:axion-release-plugin:1.13.3")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}