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
import kotlin.reflect.KClass


private val managerClazz: KClass<out PluginManager> by lazy { pluginManager::class }


fun Plugin.enable() = pluginManager.enablePlugin(this)

fun Plugin.disable() = pluginManager.disablePlugin(this)


fun Plugin.file() = JavaPlugin::class.getPrivateVar<File>("file", this)

fun File.loadPlugin(): Plugin? = pluginManager.loadPlugin(this).apply { onLoad() }


fun loadPluginByName(name: String): Plugin? = pluginsFolder.resolve("$name.jar").takeIf { it.exists() }?.loadPlugin()


fun Plugin.unload() {

	managerClazz.getPrivateVar<ArrayList<Plugin>>("plugins", pluginManager).remove(this)
	managerClazz.getPrivateVar<MutableMap<String, Plugin>>("lookupNames", pluginManager).remove(this.name)

	val commandMap = managerClazz.getPrivateVar<SimpleCommandMap>("commandMap", pluginManager)
	val knownCmds = commandMap::class.getPrivateVar<MutableMap<String, Command>>("knownCommands", commandMap)

	knownCmds.values.removeIf { it is PluginCommand && it.plugin == this && it.unregister(commandMap) }
}

