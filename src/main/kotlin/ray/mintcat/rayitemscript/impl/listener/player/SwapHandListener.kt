package ray.mintcat.rayitemscript.impl.listener.player

import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.event.player.PlayerToggleSprintEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir
import taboolib.platform.util.isNotAir

object SwapHandListener : ScriptListener {

    @SubscribeEvent
    fun onPlayerSwapHandItemsEvent(event: PlayerSwapHandItemsEvent) {
        if (!event.offHandItem.isAir) {
            RayItemScript.run(event.player, event, event.offHandItem!!)
        }
        if (!event.mainHandItem.isAir) {
            RayItemScript.run(event.player, event, event.mainHandItem!!)
        }
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "player_swap_hand"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is PlayerSwapHandItemsEvent) {
            return true
        }
        if (call.listener.list.contains("player_swap_hand_any") ||
            call.listener.list.contains("player_swap_hand_any!!")) {
            return true
        }
        if (event.offHandItem.isNotAir()) {
            if (call.listener.list.contains("player_swap_hand_off") ||
                call.listener.list.contains("player_swap_hand_off!!")) {
                return true
            }
        }
        if (event.mainHandItem.isNotAir()) {
            if (call.listener.list.contains("player_swap_hand_main") ||
                call.listener.list.contains("player_swap_hand_main!!")) {
                return true
            }
        }
        return true
    }
}