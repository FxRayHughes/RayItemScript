package ray.mintcat.rayitemscript.impl.listener.item

import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object ItemPickUpListener : ScriptListener {

    @SubscribeEvent
    fun onPlayerItemBreakEvent(event: PlayerPickupItemEvent) {
        val item = event.item.itemStack
        RayItemScript.run(event.player, event, item, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "item_pickup"

    override fun check(event: Any, call: ScriptData): Boolean {
        
        if (event !is PlayerPickupItemEvent) {
            return true
        }
        if (event.item.itemStack.isAir) {
            return false
        }
        return true
    }
}