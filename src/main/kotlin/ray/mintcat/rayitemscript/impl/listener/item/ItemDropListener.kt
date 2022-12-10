package ray.mintcat.rayitemscript.impl.listener.item

import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object ItemDropListener : ScriptListener {

    @SubscribeEvent
    fun onPlayerItemBreakEvent(event: PlayerDropItemEvent) {
        val item = event.itemDrop.itemStack
        RayItemScript.run(event.player, event, item)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "item_drop"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is PlayerDropItemEvent) {
            return true
        }
        if (event.itemDrop.itemStack.isAir) {
            return false
        }
        return true
    }
}