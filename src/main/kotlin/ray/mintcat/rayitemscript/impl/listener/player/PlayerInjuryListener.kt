package ray.mintcat.rayitemscript.impl.listener.player

import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent

object PlayerInjuryListener : ScriptListener {

    @SubscribeEvent
    fun onPlayerItemBreakEvent(event: EntityDamageEvent) {
        val player = event.entity as? Player ?: return
        RayItemScript.runAll(player, event, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "player_injury"

    override fun check(event: Any, call: ScriptData): Boolean {

        if (event !is EntityDamageEvent) {
            return true
        }
        return true
    }
}