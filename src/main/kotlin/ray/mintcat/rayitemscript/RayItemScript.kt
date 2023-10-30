package ray.mintcat.rayitemscript

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.platform.Plugin
import taboolib.platform.util.isAir
import taboolib.platform.util.isNotAir
import java.util.concurrent.ConcurrentHashMap

object RayItemScript : Plugin() {

    val listeners = ConcurrentHashMap<String, ScriptListener>()

    val script = ArrayList<ScriptData>()

    fun run(player: Player, event: Any, item: ItemStack, name: String) {
        script.forEach {
            it.listener.list.forEach { z ->
                if (z.contains(name) && item.isNotAir()) {
                    it.run(player, event, item, it)
                }
            }
        }
    }

    fun runAll(player: Player, event: Any, name: String) {
        PlayerInvUtils.getInv(player).forEach {
            if (!it.isAir) {
                run(player, event, it, name)
            }
        }
    }

}