@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.plugkat.ext

import me.camdenorrb.plugkat.struct.pluginManager
import me.camdenorrb.plugkat.struct.pluginsFolder
import org.bukkit.command.Command
import org.bukkit.command.PluginCommand
import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*


private val managerClazz: Class<out PluginManager> by lazy { pluginManager.javaClass }


fun Plugin.enable() = pluginManager.enablePlugin(this)

fun Plugin.disable() = pluginManager.disablePlugin(this)


fun File.loadPlugin(): Plugin? = pluginManager.loadPlugin(this).apply { onLoad() }

fun Plugin.file(block: (File) -> Unit) = JavaPlugin::class.java.getPrivateVar<File>("file", this) { block(it) }


fun loadPluginByName(name: String): Plugin? = pluginsFolder.resolve("$name.jar").takeIf { it.exists() }?.loadPlugin()


fun Plugin.unload() {

	managerClazz.getPrivateVar<ArrayList<Plugin>>("plugins", pluginManager) { it.remove(this) }

	managerClazz.getPrivateVar<SimpleCommandMap>("commandMap", pluginManager) { commandMap ->

		managerClazz.getPrivateVar<MutableMap<String, Plugin>>("lookupNames", pluginManager) {
			it.remove(this.name)
		}

		commandMap.javaClass.getPrivateVar<MutableMap<String, Command>>("knownCommands", commandMap) {
			it.values.removeIf { it is PluginCommand && it.plugin == this && it.unregister(commandMap) }
		}

	}

}
