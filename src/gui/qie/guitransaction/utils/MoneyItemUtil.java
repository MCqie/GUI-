package gui.qie.guitransaction.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 游戏币物品处理类
 * @author byxiaobai
 *
 */
public class MoneyItemUtil {
/**
 * 
 * @param addMoney 增加的游戏币
 * @return
 */
public static ItemStack getAddMoneyItem(String addMoney){
	ItemStack item=new ItemStack(399);
	ItemMeta itemMeta=item.getItemMeta();
	itemMeta.setDisplayName("§e增加§b"+addMoney+"§e游戏�?");
	item.setItemMeta(itemMeta);
	return item;
}
/**
 * 
 * @param reduceMoney 减少的游戏币
 * @return
 */
public static ItemStack getReduceMoneyItem(String reduceMoney){
	ItemStack item=new ItemStack(264);
	ItemMeta itemMeta=item.getItemMeta();
	itemMeta.setDisplayName("§e减少§b"+reduceMoney+"§e游戏�?");
	item.setItemMeta(itemMeta);
	return item;
}
}
