package ray.mintcat.rayitemscript.impl.listener.hook

import eos.moe.dragoncore.api.event.KeyPressEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent

object DragonKeyPressListener : ScriptListener {

    @SubscribeEvent
    fun onKeyPressEvent(event: KeyPressEvent) {
        RayItemScript.runAll(event.player, event, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "dragon_key_press"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is KeyPressEvent) {
            return true
        }
        val combo = call.action.firstOrNull { it.startsWith(name) }?.removePrefix("${name}->")
        combo?.split(",")?.forEach {
            if (!event.keys.contains(it)) {
                return false
            }
        }
        return true
    }
}