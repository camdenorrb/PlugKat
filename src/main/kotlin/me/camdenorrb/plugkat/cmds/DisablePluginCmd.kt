package me.camdenorrb.plugkat.cmds

import me.camdenorrb.katlibraries.struct.DARK_GREEN
import me.camdenorrb.katlibraries.struct.DARK_RED
import me.camdenorrb.katlibraries.struct.GREEN
import me.camdenorrb.katlibraries.struct.RED
import me.camdenorrb.plugkat.ext.disable
import me.camdenorrb.plugkat.struct.pluginManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor


class DisablePluginCmd : CommandExecutor, TabExecutor {

	override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

		val plugin = args.firstOrNull()?.let { pluginManager.getPlugin(it) } ?: return false

		if (plugin.isEnabled.not()) {
			sender.sendMessage("${DARK_RED}The plugin $RED${plugin.name} ${DARK_RED}is already disabled!")
			return true
		}

		plugin.disable()
		sender.sendMessage("${DARK_GREEN}The plugin $GREEN${plugin.name} ${DARK_GREEN}has been disabled!")

		return true
	}

	override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<String>): List<String> {
		return pluginManager.plugins.filter { it.isEnabled }.map { it.name }
	}

}