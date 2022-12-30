package ray.mintcat.rayitemscript.impl.actions

import org.bukkit.inventory.ItemStack
import ray.mintcat.rayitemscript.getString
import taboolib.platform.util.hasLore

data class ItemData(
    val lore: List<String>?,
    val nbt: String?,
    val value: String?,
    val slot: String?
) {

    fun check(itemStack: ItemStack): Boolean {
        if (nbt != null && value != null) {
            return itemStack.getString(nbt, "null") == value
        }
        lore?.forEach {
            if (itemStack.hasItemMeta() && itemStack.hasLore() && itemStack.hasLore(it)) {
                return true
            } else {
                return@forEach
            }
        }
        return false
    }

}