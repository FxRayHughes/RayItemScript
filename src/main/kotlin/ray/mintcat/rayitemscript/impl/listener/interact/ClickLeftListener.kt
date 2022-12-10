package ray.mintcat.rayitemscript.impl.listener.interact

import org.bukkit.event.player.PlayerInteractEvent
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.platform.util.isLeftClick

object ClickLeftListener : ScriptListener {

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "click_left"

    override fun check(event: Any, call: ScriptData): Boolean {
        
        if (event !is PlayerInteractEvent) {
            return true
        }
        if (event.item == null) {
            return false
        }
        return event.isLeftClick()
    }
}