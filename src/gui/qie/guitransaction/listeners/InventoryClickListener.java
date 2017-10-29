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
	 * ��֤GUI�İ�ȫ�ԣ�����������ö�����
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
	 * ����GUI��˫������Ʒ
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
					player.sendMessage("��c����ȷ�Ͻ���!");
					return;
					
				}else if(!playerInfo.isOk&&playerInfo.nextInfo.isOk){//�����û��ȷ�ϵ��Է�ȷ��
					player.sendMessage("��c���������Ʒ,�Է�ȷ�Ͻ��׵�״̬��ȡ��!");
					playerInfo.nextInfo.player.sendMessage("��c�Է���������Ʒ,ȷ�Ͻ��׵�״̬��ȡ��!");
					playerInfo.nextInfo.isOk=false;
					playerInfo.items=GuiUtil.getOwnerItems(player,clickedInventory);
					playerInfo.updataNextInfoGui();
					return;
				}
				playerInfo.items=GuiUtil.getOwnerItems(player,clickedInventory);
				playerInfo.updataNextInfoGui();
				
			}else if(slot==4){
				player.sendMessage("��a��Ʒ���³ɹ�!");
				playerInfo.items=GuiUtil.getOwnerItems(player,clickedInventory);
				playerInfo.updataNextInfoGui();
				return;
			}
		}
	}
	
	/**
	 * ������Ϸ������,���ø�������Ϸ������
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
			case 39://������Ϸ��
				addMoney(player,playerInfo,PluginData.addMoney);
				return;
			case 38:
				addMoney(player,playerInfo,PluginData.addMoney1);
				return;
			case 37:
				addMoney(player,playerInfo,PluginData.addMoney2);
				return;
			case 41://������Ϸ��
				ReduceMoney(player,playerInfo,PluginData.reduceMoney);
				return;
			case 42://������Ϸ��
				ReduceMoney(player,playerInfo,PluginData.reduceMoney1);
				return;
			case 43://������Ϸ��
				ReduceMoney(player,playerInfo,PluginData.reduceMoney2);
				return;
			}
			
		}
	}
	
	private void ReduceMoney(Player player,TransactionInfo playerInfo,double reduceMoney){
		if(playerInfo.money-reduceMoney>=0){
			playerInfo.money=playerInfo.money-reduceMoney;
			playerInfo.nextInfo.updataGuiTransactionInfo(playerInfo.money);//���¶Է�������
			playerInfo.updataGuiTransactionInfo(playerInfo.nextInfo.money);//�����Լ�������
		}else{
			player.sendMessage("��c�޷�����������Ϸ��!");
		}
	}
	
	private void addMoney(Player player,TransactionInfo playerInfo,double addMoney){
		if(VaultUtil.hasMoney(player, playerInfo.money+addMoney)){
			player.sendMessage("��a��Ϸ�����ӳɹ�!");
			playerInfo.money=playerInfo.money+addMoney;
			playerInfo.nextInfo.updataGuiTransactionInfo(playerInfo.money);//���¶Է�������
			playerInfo.updataGuiTransactionInfo(playerInfo.nextInfo.money);//�����Լ�������
		}else{
			player.sendMessage("��c��û���㹻����Ϸ��!");
		}
	}
	/**
	 * ȷ������
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
				if(!playerInfo.isOk){//�����δ׼��
					if(playerInfo.nextInfo.isOk){//���Է�׼����
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
						
						
						player.sendMessage("��e���׳ɹ�!");
						playerInfo.nextInfo.player.sendMessage("��e���׳ɹ�!");
						
						return;
					}else{
						playerInfo.isOk=true;
						player.sendMessage("��eȷ�Ͻ��׳ɹ�!");
						playerInfo.nextInfo.player.sendMessage("��c�Է���ȷ������!");
						return;
					}
					
					
				}else{
					player.sendMessage("��c���Ѿ�ȷ�Ϲ�Ҫ������!");
				}
			}
			
		}
	}
	/**
	 * ����Ƿ���������ȷ����Ʒ
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
