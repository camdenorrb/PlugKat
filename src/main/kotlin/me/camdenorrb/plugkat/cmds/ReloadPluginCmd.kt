package me.camdenorrb.plugkat.cmds

import me.camdenorrb.katlibraries.struct.DARK_GREEN
import me.camdenorrb.katlibraries.struct.GREEN
import me.camdenorrb.katlibraries.struct.RED
import me.camdenorrb.plugkat.ext.*
import me.camdenorrb.plugkat.struct.pluginManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor


class ReloadPluginCmd : CommandExecutor, TabExecutor {

	override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

		val plugin = args.firstOrNull()?.let { pluginManager.getPlugin(it) } ?: return false


		if (plugin.name.let { it == "PlugKat" || it == "KatLibraries" }) {
			sender.sendMessage("${RED}You cannot reload this plugin!")
			return true
		}

		if (plugin.isEnabled) plugin.disable()

		plugin.unload()
		plugin.file().loadPlugin()?.enable()

		sender.sendMessage("${DARK_GREEN}The plugin $GREEN${plugin.name} ${DARK_GREEN}has been reloaded!")

		return true
	}

	override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<String>): List<String> {
		return pluginManager.plugins.map { it.name }
	}

}