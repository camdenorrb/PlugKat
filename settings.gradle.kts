rootProject.name = "PlugKat"

sourceControl {
	gitRepository(java.net.URI.create("https://github.com/MiniMineCraft/MiniBus.git")) {
		producesModule("me.camdenorrb:MiniBus")
	}
}

sourceControl {
	gitRepository(java.net.URI.create("https://github.com/camdenorrb/KSpigotBasics")) {
		producesModule("me.camdenorrb:KSpigotBasics")
	}
}