package ray.mintcat.rayitemscript.impl.actions

import org.bukkit.event.Cancellable
import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData

data class ListenerData(
    val any: Boolean = false,
    val list: List<String>
) {

    fun eval(event: Any, call: ScriptData): Boolean {
        var over = false
        list.forEach { key ->
            var it = key
            if (key.contains("->")) {
                it = it.split("->").getOrNull(0) ?: return@forEach
            }
            if (it.contains("!!")) {
                (event as? Cancellable)?.isCancelled = true
                it = it.replace("!!", "")
            }
            val action = RayItemScript.listeners[it]
            if (action?.check(event, call) == true) {
                over = true
                if (any) {
                    return true
                }
                return@forEach
            }
            val temp = RayItemScript.listeners.values.firstOrNull { z ->
                it.contains(z.name)
            }
            if (temp != null) {
                over = temp.check(event, call)
                if (over && any) {
                    return true
                }
                return@forEach
            }
            return@forEach
        }
        return over
    }

}