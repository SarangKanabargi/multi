plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("pl.allegro.tech.build.axion-release")
    id("org.openapi.generator") version "5.1.1"
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.openapitools:openapi-generator-gradle-plugin:5.1.1") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

openApiValidate {
    inputSpec.value("$rootDir/api/customer-api.yaml")
}

openApiGenerate {
    inputSpec.set("$rootDir/api/customer-api.yaml")
    generatorName.set("kotlin-spring")

    groupId.set("com.example")
    version.set("${project.version}")
    packageName.set("com.example.customer")
    outputDir.set("$buildDir/generated-source")
    modelPackage.set("com.example.customer.model")
    invokerPackage.set("com.example.customer")
    modelNameSuffix.set("Dto")

    configOptions.set(
        mapOf(
            "dateLibrary" to "java8-localdatetime",
            "interfaceOnly" to "true",
            "reactive" to "true",
            "gradleBuildFile" to "true"
        )
    )
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated-source/src/main/kotlin")
        }
    }
}