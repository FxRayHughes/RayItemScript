package ray.mintcat.rayitemscript.impl.actions

import org.bukkit.entity.Player
import ray.mintcat.rayitemscript.Time
import ray.mintcat.rayitemscript.cooldown.CooldownUtil
import taboolib.module.chat.colored

data class CooldownData(
    val group: String?,
    val time: Long = 0L,
    val message: String? = null
) {

    fun check(player: Player): Boolean {
        if (group == null) {
            return true
        }
        if (CooldownUtil.check("${player.uniqueId}::${group}", time)) {
            return true
        }
        val has = CooldownUtil.map.getOrDefault("${player.uniqueId}::${group}", 0) - System.currentTimeMillis()
        player.sendMessage(message?.colored()?.replace("{time}", Time(has).toString()) ?: return false)
        return false
    }

}