@file:Suppress("UNCHECKED_CAST")

package me.camdenorrb.plugkat.ext

import me.camdenorrb.plugkat.struct.pluginManager
import me.camdenorrb.plugkat.struct.pluginsFolder
import org.bukkit.command.Command
import org.bukkit.command.PluginCommand
import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import java.io.File
import java.util.*


private val managerClazz: Class<out PluginManager> by lazy { pluginManager.javaClass }


fun Plugin.enable() = pluginManager.enablePlugin(this)

fun Plugin.disable() = pluginManager.disablePlugin(this)


fun File.loadPlugin() = pluginManager.loadPlugin(this)

fun loadPluginByName(name: String): Plugin? = pluginManager.loadPlugin(pluginsFolder.resolve("$name.jar"))


fun Plugin.unload() {

	val commandMapField = managerClazz.getDeclaredField("commandMap").apply { isAccessible = true }
	val commandMap = commandMapField.get(pluginManager) as SimpleCommandMap

	val lookupNamesField = managerClazz.getDeclaredField("lookupNames").apply { isAccessible = true }
	(lookupNamesField.get(pluginManager) as MutableMap<String, Plugin>).values.removeIf { it == this }

	val knownCommandsField = commandMap.javaClass.getDeclaredField("knownCommands").apply { isAccessible = true }
	val knownCommands = knownCommandsField.get(commandMap) as MutableMap<String, Command>
	knownCommands.values.removeIf { (it as? PluginCommand)?.plugin == this }

	val pluginsField = managerClazz.getDeclaredField("plugins").apply { isAccessible = true }
	(pluginsField.get(pluginManager) as ArrayList<Plugin>).remove(this)


	pluginsField.isAccessible = false
	commandMapField.isAccessible = false
	lookupNamesField.isAccessible = false
	knownCommandsField.isAccessible = false

}