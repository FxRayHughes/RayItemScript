package ray.mintcat.rayitemscript.impl.listener.hook

import eos.moe.dragoncore.api.event.KeyReleaseEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent

object DragonKeyReleaseListener : ScriptListener {

    @SubscribeEvent
    fun onKeyReleaseEvent(event: KeyReleaseEvent) {
        RayItemScript.runAll(event.player, event, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "dragon_key_release"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is KeyReleaseEvent) {
            return true
        }
        return true
    }
}