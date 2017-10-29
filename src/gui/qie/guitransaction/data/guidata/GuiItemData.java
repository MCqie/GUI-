package gui.qie.guitransaction.data.guidata;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiItemData {
	/**
	 * 黑色玻璃板
	 */
	private static ItemStack blackGlass=new ItemStack(Material.GLASS);
@SuppressWarnings("unused")
private Skull playerSkull;

public static final GuiItemData INSTACNE=new GuiItemData();

public static ItemStack getBlackGlass(){
	return blackGlass;
}
/**
 * 得到确定交易的物品(无任何参数)
 */
public static ItemStack getFirmTransactionItem(){//确定交易 按钮
	ItemStack item=new ItemStack(146);
	ItemMeta itemMeta=item.getItemMeta();
	itemMeta.setDisplayName("§e确定交易");
	List<String> lore=new ArrayList<String>();
	lore.add("§a点击此按钮确定交易");
	itemMeta.setLore(lore);
	item.setItemMeta(itemMeta);
	return item;
}

public static ItemStack getUpdataItem(){//更新物品 按钮
	ItemStack item=new ItemStack(Material.MILK_BUCKET);
	ItemMeta itemMeta=item.getItemMeta();
	itemMeta.setDisplayName("§a更新交易物品");
	List<String> lore=new ArrayList<String>();
	lore.add("§a点击此按钮更新交易物品(不点击有可能导致物品不正确)");
	itemMeta.setLore(lore);
	item.setItemMeta(itemMeta);
	return item;
}
}
