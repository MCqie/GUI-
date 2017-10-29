package gui.qie.guitransaction.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import gui.qie.guitransaction.data.guidata.GuiData;

public class InventoryUtil {

	  public static Inventory CloneInventory(Inventory OldInventory)
	  {
	    Inventory NewInventory = Bukkit.createInventory(OldInventory.getHolder(), OldInventory.getSize(), OldInventory.getTitle());
	    for (int i = 0; i < OldInventory.getSize(); i++)
	    {
	      ItemStack Item = OldInventory.getItem(i);
	      NewInventory.setItem(i, Item);
	    }
	    return NewInventory;
	  }
	  
	  public static Inventory CloneInventory(Inventory OldInventory, Player player)
	  {
	    Inventory NewInventory = Bukkit.createInventory(player, OldInventory.getSize(), OldInventory.getTitle());
	    for (int i = 0; i < OldInventory.getSize(); i++)
	    {
	      ItemStack Item = OldInventory.getItem(i);
	      NewInventory.setItem(i, Item);
	    }
	    return NewInventory;
	  }
	  /**
		 * 查看是否是本插件的GUI
		 * @param clickedInventory
		 * @return
		 */
		public static boolean isGui(Inventory clickedInventory){
			if(clickedInventory==null)return false;
			String inventoryName=clickedInventory.getName();
			if(inventoryName==null)return false;
			if(inventoryName.equalsIgnoreCase(GuiData.INSTACNE.guiTitle)){
				return true;
			}
			return false;
		}
}
