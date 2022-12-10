package ray.mintcat.rayitemscript.impl.listener.interact

import org.bukkit.event.player.PlayerInteractEvent
import ray.mintcat.rayitemscript.RayItemScript
import taboolib.common.platform.event.SubscribeEvent

object Listener {

    @SubscribeEvent
    fun e(event: PlayerInteractEvent) {
        val item = event.item ?: return
        RayItemScript.run(event.player, event, item, "click_")
    }

}