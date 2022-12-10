package ray.mintcat.rayitemscript.impl.listener.player

import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.event.player.PlayerToggleSprintEvent
import org.bukkit.inventory.ItemStack
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object PlayerDamageListener : ScriptListener {

    @SubscribeEvent(EventPriority.HIGHEST)
    fun onPlayerItemBreakEvent(event: EntityDamageByEntityEvent) {
        val player = event.damager
        if (player !is Player) {
            return
        }
        val list = mutableListOf<ItemStack>()
        list.addAll(player.inventory.armorContents)
        list.add(player.inventory.itemInMainHand)
        list.add(player.inventory.itemInOffHand)
        list.forEach { item ->
            if (!item.isAir()) {
                RayItemScript.run(player, event, item, name)
            }
        }
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "player_damage"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is EntityDamageByEntityEvent) {
            return true
        }
        if (event.damager !is Player) {
            return false
        }
        return true
    }
}