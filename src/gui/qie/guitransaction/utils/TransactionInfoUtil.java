package gui.qie.guitransaction.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TransactionInfoUtil {
	private static ItemStack info;
	static{
		info=new ItemStack(Material.PAPER);
		ItemMeta itemMeta=info.getItemMeta();
		itemMeta.setDisplayName("§b交易详情:");
		itemMeta.setLore(new ArrayList<String>());
		info.setItemMeta(itemMeta);
	}
/**
 * 这个的默认值为0
 * @param player 
 * @return
 */
public static ItemStack getTransactionInfo(Player player){
	ItemStack infoA=info.clone();
	ItemMeta infoAMeta=infoA.getItemMeta();
	List<String> infoALore=new ArrayList<String>();
	infoALore.add(getStr(player)+"0");
	infoAMeta.setLore(infoALore);
	infoA.setItemMeta(infoAMeta);
	return infoA;
}
/**
 * 
 * @param player 玩家
 * @param money 付出的游戏币
 * @return
 */
public static ItemStack getTransactionInfo(Player player,double money){
	ItemStack infoA=info.clone();
	ItemMeta infoAMeta=infoA.getItemMeta();
	List<String> infoALore=new ArrayList<>();
	infoALore.add(getStr(player)+money);
	infoAMeta.setLore(infoALore);
	infoA.setItemMeta(infoAMeta);
	return infoA;
}
private static String getStr(Player player){
	return "§f"+player.getName()+"§2付出的游戏币§7: §3";
}
}
