package ray.mintcat.rayitemscript.impl.listener.block

import org.bukkit.event.block.BlockPlaceEvent
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.util.isAir

object BlockPlaceListener : ScriptListener {

    @SubscribeEvent
    fun onBlockPlaceEvent(event: BlockPlaceEvent) {
        val item = event.player.inventory.itemInMainHand
        RayItemScript.run(event.player, event, item, name)
    }

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
    }

    override val name: String = "block_place"

    override fun check(event: Any, call: ScriptData): Boolean {
        
        if (event !is BlockPlaceEvent) {
            return true
        }
        if (event.itemInHand.isAir) {
            return false
        }
        return true
    }
}