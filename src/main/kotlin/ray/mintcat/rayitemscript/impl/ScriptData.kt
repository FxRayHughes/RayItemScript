package ray.mintcat.rayitemscript.impl

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import ray.mintcat.rayitemscript.impl.actions.*
import taboolib.common.platform.function.adaptPlayer
import taboolib.module.chat.colored
import taboolib.module.kether.KetherShell
import taboolib.module.kether.printKetherErrorMessage
import taboolib.platform.util.checkItem

data class ScriptData(
    val key: String,
    val listener: ListenerData,
    val item: ItemData,
    val consume: ConsumeData,
    val cooldown: CooldownData,
    val conditions: ConditionsData,
    val action: List<String>
) {

    fun run(player: Player, event: Any, itemStack: ItemStack, call: ScriptData) {
        if (!item.check(itemStack)) {
            return
        }
        if (!listener.eval(event, call)) {
            return
        }
        if (!conditions.check(player)) {
            return
        }
        if (!cooldown.check(player)) {
            return
        }
        if (conditions.probability != 100 && (0..100).random() > conditions.probability) {
            player.sendMessage(conditions.probabilityMessage?.colored() ?: return)
            return
        }
        if (player.checkItem(itemStack, consume.amount, consume.take)) {
            action.eval(player)
        } else {
            player.sendMessage(consume.message?.colored() ?: return)
        }
    }

    fun List<String>.eval(player: Player) {
        try {
            KetherShell.eval(this, sender = adaptPlayer(player))
        } catch (e: Throwable) {
            e.printKetherErrorMessage()
        }
    }

}