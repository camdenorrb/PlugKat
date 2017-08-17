package me.camdenorrb.plugkat

import me.camdenorrb.plugkat.cmds.*
import org.bukkit.plugin.java.JavaPlugin


class PlugKat : JavaPlugin() {

	override fun onLoad() { instance = this }

	override fun onEnable() {
		getCommand("loadplugin").executor = LoadPluginCmd()
		getCommand("enableplugin").executor = EnablePluginCmd()
		getCommand("unloadplugin").executor = UnloadPluginCmd()
		getCommand("reloadplugin").executor = ReloadPluginCmd()
		getCommand("disableplugin").executor = DisablePluginCmd()
	}


	companion object {

		lateinit var instance: PlugKat
			private set

	}

}