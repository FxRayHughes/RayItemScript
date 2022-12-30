package ray.mintcat.rayitemscript.impl.listener.time

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData
import ray.mintcat.rayitemscript.impl.listener.ScriptListener
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.submit

object TimeListener : ScriptListener {

    @Awake(LifeCycle.ACTIVE)
    fun load() {
        register()
        submit(async = true, period = 200) {
            Bukkit.getOnlinePlayers().forEach { player ->
                RayItemScript.runAll(player, Pair("TIMER", "X"), name)
            }
        }
    }

    override val name: String = "time_always"

    override fun check(event: Any, call: ScriptData): Boolean {
        if (event !is Pair<*, *>) {
            return false
        }
        if (event.first != "TIMER") {
            return false
        }
        return call.listener.list.contains("time_always")
    }

}