package ray.mintcat.rayitemscript.impl.actions

import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptPlayer
import taboolib.common5.Coerce
import taboolib.module.chat.colored
import taboolib.module.kether.KetherShell
import taboolib.module.kether.printKetherErrorMessage
import java.util.concurrent.CompletableFuture

data class ConditionsData(
    val probability: Int = 100,
    val permissions: String? = null,
    val message: String? = null,
    val check: List<String>
) {

    fun check(player: Player): Boolean {
        if (permissions != null && !player.hasPermission(permissions) && !player.isOp) {
            player.sendMessage(message?.colored())
            return false
        }
        return (if (check.isEmpty()) {
            CompletableFuture.completedFuture(true)
        } else {
            try {
                KetherShell.eval(check, sender = adaptPlayer(player)).thenApply {
                    Coerce.toBoolean(it)
                }
            } catch (e: Throwable) {
                e.printKetherErrorMessage()
                CompletableFuture.completedFuture(false)
            }
        }).get() ?: true
    }
}