package ray.mintcat.rayitemscript.impl.listener.item

import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object ItemConsumeListener : ScriptListener {

    @SubscribeEvent
    fun onPlayerItemBreakEvent(event: PlayerItemConsumeEvent) {
        val item = event.item
        RayItemScript.run(event.player, event, item, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "item_consume"

    override fun check(event: Any, call: ScriptData): Boolean {
        
        if (event !is PlayerItemConsumeEvent) {
            return true
        }
        if (event.item.isAir) {
            return false
        }
        return true
    }
}