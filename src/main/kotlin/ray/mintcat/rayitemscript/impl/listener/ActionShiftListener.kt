package ray.mintcat.rayitemscript.impl.listener

import org.bukkit.event.player.PlayerEvent
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

object ActionShiftListener : ScriptListener {

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "action_shift"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is PlayerEvent) {
            return true
        }
        return event.player.isSneaking
    }
}