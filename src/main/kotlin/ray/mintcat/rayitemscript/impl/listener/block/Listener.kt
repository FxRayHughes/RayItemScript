package ray.mintcat.rayitemscript.impl.listener.block

import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import ray.mintcat.rayitemscript.RayItemScript
import taboolib.common.platform.event.SubscribeEvent

object Listener {

    @SubscribeEvent
    fun onBlockBreakEvent(event: BlockBreakEvent) {
        val item = event.player.inventory.itemInMainHand
        RayItemScript.run(event.player, event, item)
    }

    @SubscribeEvent
    fun onBlockPlaceEvent(event: BlockPlaceEvent) {
        val item = event.player.inventory.itemInMainHand
        RayItemScript.run(event.player, event, item)
    }

}