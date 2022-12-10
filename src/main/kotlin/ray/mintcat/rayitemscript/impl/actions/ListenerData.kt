package ray.mintcat.rayitemscript.impl.actions

import org.bukkit.event.Cancellable
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData

data class ListenerData(
    val list: List<String>
) {

    fun eval(event: Any, call: ScriptData): Boolean {
        return !(list.map {
            val action = RayItemScript.listeners[it]
            if (action == null) {
                val temp = RayItemScript.listeners.values.firstOrNull { z -> it.startsWith(z.name) }
                if (it.contains("!!")) {
                    (event as? Cancellable)?.isCancelled = true
                }
                temp?.check(event, call) ?: return true
            } else {
                if (it.contains("!!")) {
                    (event as? Cancellable)?.isCancelled = true
                }
                action.check(event, call)
            }
        }.contains(false))
    }

}