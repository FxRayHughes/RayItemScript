package ray.mintcat.rayitemscript.impl.listener.item

import org.bukkit.event.player.PlayerItemBreakEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object ItemBreakListener : ScriptListener {

    @SubscribeEvent
    fun onPlayerItemBreakEvent(event: PlayerItemBreakEvent) {
        val item = event.brokenItem
        RayItemScript.run(event.player, event, item, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "item_break"

    override fun check(event: Any, call: ScriptData): Boolean {
        
        if (event !is PlayerItemBreakEvent) {
            return true
        }
        if (event.brokenItem.isAir) {
            return false
        }
        return true
    }
}