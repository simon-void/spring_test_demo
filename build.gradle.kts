plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	val kotlinVersion = "1.8.21"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
}

group = "de.beispiel"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

kotlin {
	// uses org.gradle.java.installations.auto-download=false in gradle.properties to disable auto provisioning of JDK
	jvmToolchain(17)
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude("org.junit.vintage", "junit-vintage-engine")
		exclude("org.junit", "junit")
		exclude("org.mockito", "mockito-core")
		exclude("org.mockito", "mockito-junit-jupiter")
		exclude("org.assertj", "assertj-core")
	}
	testImplementation("com.ninja-squad:springmockk:4.0.2")
	testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
