package ray.mintcat.rayitemscript.impl.listener

import ray.mintcat.rayitemscript.RayItemScript
import ray.mintcat.rayitemscript.impl.ScriptData

interface ScriptListener {

    val name: String

    fun check(event: Any, call: ScriptData): Boolean

    fun register() {
        RayItemScript.listeners[name] = this
    }

}