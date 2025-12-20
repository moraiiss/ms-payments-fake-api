plugins {
	java
    checkstyle

    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

group = "com.payments-fake-api"
version = libs.versions.app
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
	implementation(rootProject.libs.bundles.spring.starters)
    implementation(rootProject.libs.bundles.spring.modules)

    compileOnly(rootProject.libs.project.lombok)
    annotationProcessor(rootProject.libs.project.lombok)

    runtimeOnly(rootProject.libs.postrgres.database)

    testImplementation(rootProject.libs.bundles.spring.testers)
    testRuntimeOnly(rootProject.libs.junit.platform.launcher)
}

tasks.withType<Test> {
	useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.12.7"
    configFile = rootProject.file("config/checkstyle.xml")
    isIgnoreFailures = false
    maxWarnings = 0
    maxErrors = 0
}
