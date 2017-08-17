package me.camdenorrb.plugkat.ext

import me.camdenorrb.plugkat.struct.pluginManager
import me.camdenorrb.plugkat.struct.pluginsFolder
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import java.io.File
import java.util.*


private val managerClazz: Class<out PluginManager> by lazy { pluginManager::class.java }


fun Plugin.enable() = pluginManager.enablePlugin(this)

fun Plugin.disable() = pluginManager.disablePlugin(this)


fun File.loadPlugin() = pluginManager.loadPlugin(this)

fun loadPluginByName(name: String): Plugin? = pluginManager.loadPlugin(pluginsFolder.resolve("$name.jar"))


fun Plugin.unload() {

	val lookupNamesField = managerClazz.getDeclaredField("lookupNames").apply { isAccessible = true }
	(lookupNamesField.get(pluginManager) as MutableMap<*, *>).values.removeIf { it == this }
	lookupNamesField.isAccessible = false

	val pluginsField = managerClazz.getDeclaredField("plugins").apply { isAccessible = true }
	(pluginsField.get(pluginManager) as ArrayList<*>).remove(this)
	pluginsField.isAccessible = false

}