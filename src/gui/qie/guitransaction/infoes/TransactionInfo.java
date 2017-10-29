package gui.qie.guitransaction.infoes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import gui.qie.guitransaction.utils.GuiUtil;
import gui.qie.guitransaction.utils.TransactionInfoUtil;

public class TransactionInfo {
	public TransactionInfo nextInfo;
	/**
	 * ����GUI
	 */
	public Inventory transactionGui;
	/**
	 * Ը�⸶������Ʒ
	 */
	public ItemStack[] items=new ItemStack[6];;
	public Player player;
	/**
	 * �Լ�Ը�⸶������Ϸ��
	 */
	public double money=0;
	/**
	 * �����Ƿ��Ѿ�ȡ��
	 */
	public boolean isCancel=false;
	
	/**
	 * �Ƿ��Ѿ�ȷ��Ҫ����
	 */
	public boolean isOk=false;
	public TransactionInfo(Player player,TransactionInfo nextInfo,Inventory transactionGui){
		this.player=player;
		this.nextInfo=nextInfo;
		this.transactionGui=transactionGui;
		items=new ItemStack[6];
	}
	public TransactionInfo(Player player){
		this.player=player;
	}
	public void loadNextInfo(TransactionInfo nextInfo){
		this.nextInfo=nextInfo;
	}
	/**
	 * ˢ�¶Է���GUI
	 */
	//@SuppressWarnings("deprecation")
	public void updataNextInfoGui(){
		nextInfo.transactionGui.setItem(14, items[0]);
		nextInfo.transactionGui.setItem(15, items[1]);
	     nextInfo.transactionGui.setItem(16, items[2]);
	     nextInfo.transactionGui.setItem(23, items[3]);
	     nextInfo.transactionGui.setItem(24, items[4]);
	     nextInfo.transactionGui.setItem(25, items[5]);
	}
	/**
	 * ����GUI�Ľ������鰴ť
	 * @param money �Է�Ը�⸶������Ϸ��
	 */
	public void updataGuiTransactionInfo(double money){
		transactionGui.setItem(36, TransactionInfoUtil.getTransactionInfo(player,this.money));
		transactionGui.setItem(44, TransactionInfoUtil.getTransactionInfo(player,money));
		
	}
	
	public void setTransactionCancel(ItemStack[] items){
		if(items!=null){
			for(ItemStack item:items){
				if(item!=null)
				player.getInventory().addItem(item);
			}
		}
		isCancel=true;
		
		if(nextInfo!=null){
			if(!nextInfo.isCancel){//������滹û��ȡ������
				nextInfo.player.closeInventory();
			}
			
		}
		
	}
	public void setTransactionCancel(Inventory inv){
		items=GuiUtil.getOwnerItems(player,inv);
		Inventory playerInventory=player.getInventory();
		if(items!=null){
			for(ItemStack item:items){
				playerInventory.setItem(playerInventory.firstEmpty(), item);;
				player.updateInventory();
			}
		}
		isCancel=true;
		
		if(nextInfo!=null){
			if(!nextInfo.isCancel){//������滹û��ȡ������
				nextInfo.player.closeInventory();
			}
			
		}
		
	}
}
