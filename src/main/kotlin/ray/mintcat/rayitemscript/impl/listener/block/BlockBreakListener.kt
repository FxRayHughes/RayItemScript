package ray.mintcat.rayitemscript.impl.listener.block

import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerInteractEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object BlockBreakListener : ScriptListener {

    @SubscribeEvent
    fun onBlockBreakEvent(event: BlockBreakEvent) {
        val item = event.player.inventory.itemInMainHand
        RayItemScript.run(event.player, event, item, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "block_break"

    override fun check(event: Any, call: ScriptData): Boolean {

        if (event !is BlockBreakEvent) {
            return true
        }
        if (event.player.inventory.itemInMainHand.isAir) {
            return false
        }
        return true
    }
}