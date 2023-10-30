package ray.mintcat.rayitemscript.impl.listener.action

import org.bukkit.event.player.PlayerEvent
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

object ActionNotShiftListener : ScriptListener {

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "action_shift_not"

    override fun check(event: Any, call: ScriptData): Boolean {
        
        if (event !is PlayerEvent) {
            return true
        }
        return !event.player.isSneaking
    }
}