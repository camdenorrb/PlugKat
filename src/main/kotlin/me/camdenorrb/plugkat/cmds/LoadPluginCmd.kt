package me.camdenorrb.plugkat.cmds

import me.camdenorrb.katlibraries.struct.DARK_GREEN
import me.camdenorrb.katlibraries.struct.DARK_RED
import me.camdenorrb.katlibraries.struct.GREEN
import me.camdenorrb.katlibraries.struct.RED
import me.camdenorrb.plugkat.ext.enable
import me.camdenorrb.plugkat.ext.file
import me.camdenorrb.plugkat.ext.loadPluginByName
import me.camdenorrb.plugkat.struct.pluginManager
import me.camdenorrb.plugkat.struct.pluginsFolder
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter


class LoadPluginCmd : CommandExecutor, TabCompleter {

	override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

		val name = args.firstOrNull() ?: return false

		val foundPlugin = pluginManager.plugins.find { it.file().nameWithoutExtension == name }


		if (foundPlugin != null) {
			sender.sendMessage("${DARK_RED}The plugin $RED${foundPlugin.name} ${DARK_RED}is already loaded!")
			return true
		}

		val plugin = loadPluginByName(name)?.apply { enable() } ?: return false
		sender.sendMessage("${DARK_GREEN}The plugin $GREEN${plugin.name} ${DARK_GREEN}has been loaded!")

		return true
	}


	override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<String>): List<String> {
		return pluginsFolder.list().filter { it.endsWith(".jar") }.map { it.removeSuffix(".jar") }
	}

}