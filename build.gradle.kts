
plugins {
    id("java")
    id("io.freefair.lombok") version "6.6.1"

}

group = "io.github.bsl-diagnostic"
version = "0.1"

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {

    // ### Main project
    implementation("io.github.1c-syntax:bsl-language-server:0.20.0")

    // ### commons utils
    implementation("commons-io", "commons-io", "2.11.0")
    implementation("org.apache.commons", "commons-lang3", "3.12.0")
    implementation("commons-beanutils", "commons-beanutils", "1.9.4")
    implementation("org.apache.commons", "commons-collections4", "4.4")

    // ### TEST
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
//    withSourcesJar()
//    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}