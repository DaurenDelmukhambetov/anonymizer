plugins {
    kotlin("jvm") version "1.5.30"
    application
}

application {
    mainClassName = "org.dauren.AnonymizerAppKt"
}


group = "org.dauren"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("io.ktor:ktor-server-core:1.6.4")
    implementation("io.ktor:ktor-server-netty:1.6.4")
    implementation("io.ktor:ktor-jackson:1.6.4")

    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation("com.github.javafaker:javafaker:1.0.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("io.ktor:ktor-server-test-host:1.6.4")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.30")
    testImplementation("io.ktor:ktor-client-core:1.6.4")
    testImplementation("io.ktor:ktor-client-cio:1.6.4")
}

tasks.test {
    useJUnitPlatform()
}