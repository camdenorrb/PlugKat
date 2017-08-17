package me.camdenorrb.plugkat.cmds

import me.camdenorrb.katlibraries.struct.DARK_GREEN
import me.camdenorrb.katlibraries.struct.DARK_RED
import me.camdenorrb.katlibraries.struct.GREEN
import me.camdenorrb.katlibraries.struct.RED
import me.camdenorrb.plugkat.ext.enable
import me.camdenorrb.plugkat.struct.pluginManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


class EnablePluginCmd : CommandExecutor {

	override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

		val plugin = args.firstOrNull()?.let { pluginManager.getPlugin(it) } ?: return false

		if (plugin.isEnabled) {
			sender.sendMessage("${DARK_RED}The plugin $RED${plugin.name} ${DARK_RED}is already enabled!")
			return true
		}

		plugin.enable()
		sender.sendMessage("${DARK_GREEN}The plugin $GREEN${plugin.name} ${DARK_GREEN}has been enabled!")

		return true
	}

}