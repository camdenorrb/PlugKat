package me.camdenorrb.plugkat

import me.camdenorrb.kspigotbasics.ext.register
import me.camdenorrb.plugkat.cmds.*
import org.bukkit.plugin.java.JavaPlugin



lateinit var plugKat: PlugKat private set


class PlugKat : JavaPlugin() {

	override fun onLoad() {
		plugKat = this
	}

	override fun onEnable() {

		register(
			"loadplugin" to LoadPluginCmd(),
			"enableplugin" to EnablePluginCmd(),
			"unloadplugin" to UnloadPluginCmd(),
			"reloadplugin" to ReloadPluginCmd(),
			"disableplugin" to DisablePluginCmd()
		)

	}

}