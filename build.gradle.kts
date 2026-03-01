plugins {
	id("java")
	id("io.freefair.lombok") version "9.2.0"
	id("me.champeau.jmh") version "0.7.3"
}

group = "online.vonarx.hslu.ad"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
	useJUnitPlatform()
}
