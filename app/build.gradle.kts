import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.brennaswitzer"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")
    implementation("junit:junit:4.12")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}