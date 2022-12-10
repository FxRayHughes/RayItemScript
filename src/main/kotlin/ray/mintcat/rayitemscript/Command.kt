package ray.mintcat.rayitemscript

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import ray.mintcat.rayitemscript.cooldown.CooldownUtil
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.expansion.createHelper

@CommandHeader("rayitemscript", ["ris"])
object Command {


    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val cooldown = subCommand {
        dynamic(comment = "player") {
            dynamic(comment = "group") {
                execute<CommandSender> { sender, context, argument ->
                    val player = Bukkit.getPlayerExact(context.argument(-1)) ?: return@execute
                    val group = context.argument(0)
                    CooldownUtil.map.remove("${player.uniqueId}::${group}")
                    sender.sendMessage("&d冷却已重置")
                }
            }
        }
    }

    @CommandBody
    val reload = subCommand {
        Loader.load()
    }

}