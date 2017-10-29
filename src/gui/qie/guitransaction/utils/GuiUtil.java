package gui.qie.guitransaction.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiUtil {
	/**
	 * 得到自己GUI中放的物�?
	 * @param player
	 * @param clickedInventory
	 * @return
	 */
	/**
	 * 
	 * @param player
	 * @param clickedInventory
	 * @return
	 */
	public static ItemStack[] getOwnerItems(Player player,Inventory clickedInventory){
		ItemStack[] items=new ItemStack[6];
		items[0]=clickedInventory.getItem(10);;
		
		
		items[1]=clickedInventory.getItem(11);
		items[2]=clickedInventory.getItem(12);
		items[3]=clickedInventory.getItem(19);
		items[4]=clickedInventory.getItem(20);
		items[5]=clickedInventory.getItem(21);
		return items;
		
	}
}
