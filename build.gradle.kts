plugins {
	kotlin("jvm") version "1.6.21"
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.camdenorrb"
version = "1.1.0"

repositories {
	mavenCentral()
	maven("https://hub.spigotmc.org/nexus/content/repositories/public/") {
		name = "SpigotMC"
	}
}

dependencies {
	compileOnly(kotlin("stdlib-jdk8"))
	compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
	compileOnly("me.camdenorrb:MiniBus:1.4.0")
	compileOnly("me.camdenorrb:KSpigotBasics:1.1.0")
}