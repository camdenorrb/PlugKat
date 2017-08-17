package me.camdenorrb.plugkat.cmds

import me.camdenorrb.katlibraries.struct.DARK_GREEN
import me.camdenorrb.katlibraries.struct.DARK_RED
import me.camdenorrb.katlibraries.struct.GREEN
import me.camdenorrb.katlibraries.struct.RED
import me.camdenorrb.plugkat.ext.loadPluginByName
import me.camdenorrb.plugkat.struct.pluginManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


class LoadPluginCmd : CommandExecutor {

	override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

		val name = args.firstOrNull() ?: return false

		val foundPlugin = pluginManager.getPlugin(name)


		if (foundPlugin != null) {
			sender.sendMessage("${DARK_RED}The plugin $RED${foundPlugin.name} ${DARK_RED}is already loaded!")
			return true
		}


		val plugin = loadPluginByName(name) ?: return false
		sender.sendMessage("${DARK_GREEN}The plugin $GREEN${plugin.name} ${DARK_GREEN}has been loaded!")

		return true
	}

}