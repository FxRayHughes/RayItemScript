package ray.mintcat.rayitemscript.impl.listener.player

import org.bukkit.event.entity.PlayerDeathEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent

object PlayerDeathListener : ScriptListener {

    @SubscribeEvent(EventPriority.HIGHEST)
    fun onPlayerItemBreakEvent(event: PlayerDeathEvent) {
        val player = event.entity
        RayItemScript.runAll(player, event, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "player_death"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is PlayerDeathEvent) {
            return true
        }
        return true
    }
}