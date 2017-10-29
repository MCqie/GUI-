package gui.qie.guitransaction.listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import gui.qie.guitransaction.data.PluginData;
import gui.qie.guitransaction.data.TransactionsData;
import gui.qie.guitransaction.infoes.TransactionInfo;
import gui.qie.guitransaction.utils.GuiUtil;
import gui.qie.guitransaction.utils.InventoryUtil;
import gui.qie.guitransaction.utils.VaultUtil;

public class InventoryClickListener implements Listener{
	/**
	 * 保证GUI的安全性（不让玩家乱拿东西）
	 * @param evt
	 */
	@EventHandler
	public void keepInventoryGuard(InventoryClickEvent evt){
		Inventory clickedInventory=evt.getClickedInventory();
		if(InventoryUtil.isGui(clickedInventory)){
			Player player=(Player) evt.getWhoClicked();
			HashMap<Player,TransactionInfo> infoMap=TransactionsData.infoMap;
			if(infoMap.get(player)==null){
				player.closeInventory();
				return;}
			
			if(!isOwnerItem(evt.getSlot())){
				evt.setCancelled(true);
			}
			
		}
	}
	/**
	 * 更新GUI中双方的物品
	 */
	@EventHandler
	public void updataGui(InventoryClickEvent evt){
		Inventory clickedInventory=evt.getView().getTopInventory();
		if(InventoryUtil.isGui(clickedInventory)){
			Player player=(Player) evt.getWhoClicked();
			HashMap<Player,TransactionInfo> infoMap=TransactionsData.infoMap;
			final TransactionInfo playerInfo=infoMap.get(player);
			if(playerInfo==null){
				player.closeInventory();
				return;}
			
			playerInfo.items=GuiUtil.getOwnerItems(player,clickedInventory);
			playerInfo.updataNextInfoGui();
			
			int slot=evt.getSlot();
			if(isOwnerItem(slot)){
				if(playerInfo.isOk){
					evt.setCancelled(true);
					player.sendMessage("§c您已确认交易!");
					return;
					
				}else if(!playerInfo.isOk&&playerInfo.nextInfo.isOk){//该玩家没有确认但对方确认
					player.sendMessage("§c你更改了物品,对方确认交易的状态已取消!");
					playerInfo.nextInfo.player.sendMessage("§c对方更改了物品,确认交易的状态已取消!");
					playerInfo.nextInfo.isOk=false;
					playerInfo.items=GuiUtil.getOwnerItems(player,clickedInventory);
					playerInfo.updataNextInfoGui();
					return;
				}
				playerInfo.items=GuiUtil.getOwnerItems(player,clickedInventory);
				playerInfo.updataNextInfoGui();
				
			}else if(slot==4){
				player.sendMessage("§a物品更新成功!");
				playerInfo.items=GuiUtil.getOwnerItems(player,clickedInventory);
				playerInfo.updataNextInfoGui();
				return;
			}
		}
	}
	
	/**
	 * 更新游戏币详情,设置付出的游戏币数量
	 * @param evt
	 */
	@EventHandler
	public void setMoney(InventoryClickEvent evt){
		Inventory clickedInventory=evt.getView().getTopInventory();
		if(InventoryUtil.isGui(clickedInventory)){
			Player player=(Player) evt.getWhoClicked();
			HashMap<Player,TransactionInfo> infoMap=TransactionsData.infoMap;
			TransactionInfo playerInfo=infoMap.get(player);
			
			if(playerInfo==null){
				player.closeInventory();
				return;}
			switch(evt.getSlot()){
			case 39://增加游戏币
				addMoney(player,playerInfo,PluginData.addMoney);
				return;
			case 38:
				addMoney(player,playerInfo,PluginData.addMoney1);
				return;
			case 37:
				addMoney(player,playerInfo,PluginData.addMoney2);
				return;
			case 41://减少游戏币
				ReduceMoney(player,playerInfo,PluginData.reduceMoney);
				return;
			case 42://减少游戏币
				ReduceMoney(player,playerInfo,PluginData.reduceMoney1);
				return;
			case 43://减少游戏币
				ReduceMoney(player,playerInfo,PluginData.reduceMoney2);
				return;
			}
			
		}
	}
	
	private void ReduceMoney(Player player,TransactionInfo playerInfo,double reduceMoney){
		if(playerInfo.money-reduceMoney>=0){
			playerInfo.money=playerInfo.money-reduceMoney;
			playerInfo.nextInfo.updataGuiTransactionInfo(playerInfo.money);//更新对方的详情
			playerInfo.updataGuiTransactionInfo(playerInfo.nextInfo.money);//更新自己的详情
		}else{
			player.sendMessage("§c无法继续减少游戏币!");
		}
	}
	
	private void addMoney(Player player,TransactionInfo playerInfo,double addMoney){
		if(VaultUtil.hasMoney(player, playerInfo.money+addMoney)){
			player.sendMessage("§a游戏币增加成功!");
			playerInfo.money=playerInfo.money+addMoney;
			playerInfo.nextInfo.updataGuiTransactionInfo(playerInfo.money);//更新对方的详情
			playerInfo.updataGuiTransactionInfo(playerInfo.nextInfo.money);//更新自己的详情
		}else{
			player.sendMessage("§c你没有足够的游戏币!");
		}
	}
	/**
	 * 确定交易
	 * @param evt
	 */
	@EventHandler
	public void transactionConfirm(InventoryClickEvent evt){
		Inventory clickedInventory=evt.getView().getTopInventory();
		if(InventoryUtil.isGui(clickedInventory)){

			Player player=(Player) evt.getWhoClicked();
			String player1 = player.getName();
			HashMap<Player,TransactionInfo> infoMap=TransactionsData.infoMap;
			TransactionInfo playerInfo=infoMap.get(player);
			if(playerInfo==null){
				player.closeInventory();
				return;}
			int slot=evt.getSlot();
			if(slot==40){
				if(!playerInfo.isOk){//此玩家未准备
					if(playerInfo.nextInfo.isOk){//但对方准备了
						playerInfo.isOk=true;
						if(playerInfo.items!=null){
							for(ItemStack item:playerInfo.items){
								if(item==null){
									continue;
								}
								
								if(item!=null)
								playerInfo.nextInfo.player.getInventory().addItem(item);
							}
							playerInfo.items=null;
						}
						if(playerInfo.nextInfo.items!=null){
							for(ItemStack item:playerInfo.nextInfo.items){
								if(item!=null)
								playerInfo.player.getInventory().addItem(item);
							}
							playerInfo.nextInfo.items=null;
						}
						VaultUtil.getEconomy().withdrawPlayer(player1, playerInfo.money);
						VaultUtil.getEconomy().depositPlayer(playerInfo.nextInfo.player.getName(), playerInfo.money);
						VaultUtil.getEconomy().withdrawPlayer(playerInfo.nextInfo.player.getName(),playerInfo.nextInfo.money);
						VaultUtil.getEconomy().depositPlayer(player1, playerInfo.nextInfo.money);
						
						infoMap.put(player, null);
						infoMap.put(playerInfo.nextInfo.player, null);
						
						playerInfo.player.closeInventory();
						playerInfo.nextInfo.player.closeInventory();
						
						
						player.sendMessage("§e交易成功!");
						playerInfo.nextInfo.player.sendMessage("§e交易成功!");
						
						return;
					}else{
						playerInfo.isOk=true;
						player.sendMessage("§e确认交易成功!");
						playerInfo.nextInfo.player.sendMessage("§c对方已确定交易!");
						return;
					}
					
					
				}else{
					player.sendMessage("§c你已经确认过要交易了!");
				}
			}
			
		}
	}
	/**
	 * 玩家是否点击的是正确的物品
	 * @param Slot
	 * @return
	 */
	private boolean isOwnerItem(int Slot){
		switch (Slot){
		case 10:
			return true;
	    case 11:
			return true;
        case 12:
        	return true;
        case 19:
        	return true;
        case 20:
        	return true;
        case 21:
        	return true;
        default:
        	return false;
	}
  }
}
