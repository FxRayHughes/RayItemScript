package ray.mintcat.rayitemscript.impl.actions

import org.bukkit.entity.Player
import ray.mintcat.rayitemscript.Time
import ray.mintcat.rayitemscript.cooldown.CooldownUtil
import taboolib.common5.compileJS
import taboolib.module.chat.colored
import taboolib.platform.compat.replacePlaceholder
import taboolib.platform.util.sendActionBar

data class CooldownData(
    val group: String?,
    val time: String? = "0",
    val message: String? = null,
    val actionbar: Boolean = false
) {

    fun getTime(player: Player): Long {
        val ct = time?.replacePlaceholder(player)?.compileJS()?.eval()?.toString()?.toLongOrNull() ?: 0L
        return if (ct <= 0) {
            0
        } else {
            ct
        }
    }

    fun check(player: Player): Boolean {
        if (group == null) {
            return true
        }
        if (CooldownUtil.check("${player.uniqueId}::${group}", getTime(player))) {
            return true
        }
        val has = CooldownUtil.map.getOrDefault("${player.uniqueId}::${group}", 0) - System.currentTimeMillis()
        if (actionbar) {
            player.sendActionBar(message?.colored()?.replace("{time}", Time(has).toString()) ?: return false)
        } else {
            player.sendMessage(message?.colored()?.replace("{time}", Time(has).toString()) ?: return false)
        }
        return false
    }

}
