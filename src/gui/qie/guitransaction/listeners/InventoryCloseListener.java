package gui.qie.guitransaction.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import gui.qie.guitransaction.data.TransactionsData;
import gui.qie.guitransaction.infoes.TransactionInfo;
import gui.qie.guitransaction.utils.InventoryUtil;

public class InventoryCloseListener implements Listener{
	/**
	 * 交易取消
	 * @param evt
	 */
	@EventHandler
	public void transactionCancel(InventoryCloseEvent evt){
		Inventory topInventory=evt.getView().getTopInventory();
		if(InventoryUtil.isGui(topInventory)){
			Player player=(Player)evt.getPlayer();
			HashMap<Player,TransactionInfo> infoMap=TransactionsData.infoMap;
			TransactionInfo playerInfo=infoMap.get(player);
			if(playerInfo==null)return;
			ItemStack[] items=new ItemStack[6];
			items[0]=topInventory.getItem(10);
			items[1]=topInventory.getItem(11);
			items[2]=topInventory.getItem(12);
			items[3]=topInventory.getItem(19);
			items[4]=topInventory.getItem(20);
			items[5]=topInventory.getItem(21);
			playerInfo.setTransactionCancel(items);
			player.updateInventory();
			infoMap.put(player, null);
		}
	}
}
