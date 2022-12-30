package ray.mintcat.rayitemscript

import eos.moe.dragoncore.api.SlotAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object PlayerInvUtils {

    fun dragonIsEnable(): Boolean {
        return Bukkit.getPluginManager().isPluginEnabled("DragonCore")
    }

    fun getInv(player: Player): MutableList<ItemStack> {
        val list = mutableListOf<ItemStack>()
        list.addAll(player.inventory.armorContents)
        list.add(player.inventory.itemInMainHand)
        list.add(player.inventory.itemInOffHand)
        if (dragonIsEnable()) {
            list.addAll(SlotAPI.getCacheAllSlotItem(player).values)
        }
        return list
    }

}