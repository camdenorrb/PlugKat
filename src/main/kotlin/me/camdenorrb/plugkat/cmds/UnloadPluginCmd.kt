package me.camdenorrb.plugkat.cmds

import me.camdenorrb.katlibraries.struct.DARK_GREEN
import me.camdenorrb.katlibraries.struct.DARK_RED
import me.camdenorrb.katlibraries.struct.GREEN
import me.camdenorrb.katlibraries.struct.RED
import me.camdenorrb.plugkat.ext.unload
import me.camdenorrb.plugkat.struct.pluginManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


class UnloadPluginCmd : CommandExecutor {

	override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {

		val name = args.firstOrNull() ?: return false

		val plugin = pluginManager.getPlugin(name)


		if (plugin == null) {
			sender.sendMessage("${DARK_RED}The plugin $RED$name ${DARK_RED}isn't loaded!")
			return true
		}


		plugin.unload()
		sender.sendMessage("${DARK_GREEN}The plugin $GREEN${plugin.name} ${DARK_GREEN}has been unloaded!")

		return true
	}

}