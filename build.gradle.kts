plugins {
    application
    id("org.openapi.generator") version "7.0.1" // change to 6.6.0, re-run openApiGenerate and the Main class will work again
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.swagger.core.v3:swagger-annotations:2.2.16")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

openApiGenerate {
    generatorName = "java"
    inputSpec = "$rootDir/src/main/resources/example-schema.json"
    apiPackage = "com.example.api"
    modelPackage = "com.example.api.model"
    generateModelDocumentation = false
    generateApiDocumentation = false
    generateApiTests.set(false)
    generateModelTests.set(false)
    configOptions.put("openApiNullable", "false")
    configOptions.put("library", "native")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    compileJava {
        dependsOn(":openApiGenerate")
    }
}

sourceSets["main"].java {
    srcDir("${layout.buildDirectory.get()}/generate-resources/main/src/main/java")
}

