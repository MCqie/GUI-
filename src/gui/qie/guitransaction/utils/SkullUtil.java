package gui.qie.guitransaction.utils;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullUtil {
public static ItemStack getSkull(Player player){
	ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
    skullMeta.setOwner(player.getName());
    skullMeta.setDisplayName("¡ì2½»Ò×Íæ¼Ò¡ì7: "+player.getName());
    skull.setItemMeta(skullMeta);
	return skull;
	
}
}
