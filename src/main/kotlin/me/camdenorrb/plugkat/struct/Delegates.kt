package me.camdenorrb.plugkat.struct

import me.camdenorrb.katlibraries.struct.server
import me.camdenorrb.plugkat.PlugKat
import org.bukkit.plugin.PluginManager
import java.io.File


val plugKat: PlugKat by lazy { PlugKat.instance }

val pluginsFolder: File by lazy { plugKat.dataFolder.parentFile }

val pluginManager: PluginManager by lazy { server.pluginManager }