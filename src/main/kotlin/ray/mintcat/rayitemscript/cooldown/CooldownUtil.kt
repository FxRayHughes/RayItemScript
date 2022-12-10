package ray.mintcat.rayitemscript.cooldown

import java.util.concurrent.ConcurrentHashMap

object CooldownUtil {

    val map = ConcurrentHashMap<String, Long>()

    fun check(string: String, time: Long): Boolean {
        val now = map.getOrDefault(string, 0L)
        if (now < System.currentTimeMillis()) {
            map[string] = System.currentTimeMillis() + time
            return true
        }
        return false
    }

}