plugins {
	java
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.payments-fake-api"
version = "1.0.0"
description = "Project simulate payment api"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.5.6")
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.6")

//    implementation("org.springframework.boot:spring-boot-starter-actuator:3.5.6")
//    implementation("io.micrometer:micrometer-registry-otlp:1.15.5")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")

    runtimeOnly("org.postgresql:postgresql:42.7.8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
