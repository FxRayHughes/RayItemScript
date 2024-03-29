package ray.mintcat.rayitemscript.impl.listener.player

import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.event.player.PlayerToggleSprintEvent
import org.bukkit.inventory.ItemStack
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object PlayerRunListener : ScriptListener {

    @SubscribeEvent
    fun onPlayerItemBreakEvent(event: PlayerToggleSprintEvent) {
        RayItemScript.runAll(event.player, event, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "player_run"

    override fun check(event: Any, call: ScriptData): Boolean {

        if (event !is PlayerToggleSprintEvent) {
            return true
        }
        return true
    }
}