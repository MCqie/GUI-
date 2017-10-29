package gui.qie.guitransaction.data.guidata;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import gui.qie.guitransaction.data.PluginData;
import gui.qie.guitransaction.utils.InventoryUtil;
import gui.qie.guitransaction.utils.MoneyItemUtil;
import gui.qie.guitransaction.utils.SkullUtil;
import gui.qie.guitransaction.utils.TransactionInfoUtil;

public class GuiData {
	public static final GuiData INSTACNE=new GuiData();
public String guiTitle="§b出售菜单 双方都需要提供物�?";

private static Inventory transactionGui;
GuiData(){
	transactionGui=Bukkit.createInventory(null, 45,guiTitle);
	for(int i=0;i<7;i++){
		transactionGui.setItem(i+1, GuiItemData.getBlackGlass());
	}
	for(int i=0;i<9;i++){
		transactionGui.setItem(i+27, GuiItemData.getBlackGlass());
	}
	transactionGui.setItem(9, GuiItemData.getBlackGlass());
	transactionGui.setItem(13, GuiItemData.getBlackGlass());
	transactionGui.setItem(17, GuiItemData.getBlackGlass());
	transactionGui.setItem(18, GuiItemData.getBlackGlass());
	transactionGui.setItem(22, GuiItemData.getBlackGlass());
	transactionGui.setItem(26, GuiItemData.getBlackGlass());
	
	transactionGui.setItem(4, GuiItemData.getUpdataItem());//鏇存柊鐗╁搧
	transactionGui.setItem(40, GuiItemData.getFirmTransactionItem());//纭畾浜ゆ槗
}
public Inventory getTransactionGui(Player player,Player playerB){
	Inventory gui=InventoryUtil.CloneInventory(transactionGui);
	//澶撮�?
	gui.setItem(0, SkullUtil.getSkull(player));
	gui.setItem(8, SkullUtil.getSkull(playerB));
	
	//涓や釜浜ゆ槗璇︽�?
	gui.setItem(36, TransactionInfoUtil.getTransactionInfo(player));
	gui.setItem(44, TransactionInfoUtil.getTransactionInfo(playerB));
	
	//澧炲噺娓告垙甯�
	gui.setItem(39, MoneyItemUtil.getAddMoneyItem(PluginData.addMoney.toString()));//澧炲�?
	gui.setItem(38, MoneyItemUtil.getAddMoneyItem(PluginData.addMoney1.toString()));//澧炲�?
	gui.setItem(37, MoneyItemUtil.getAddMoneyItem(PluginData.addMoney2.toString()));//澧炲�?
	
	
	
	gui.setItem(41, MoneyItemUtil.getReduceMoneyItem(PluginData.reduceMoney.toString()));//鍑忓�?
	gui.setItem(42, MoneyItemUtil.getReduceMoneyItem(PluginData.reduceMoney1.toString()));
	gui.setItem(43, MoneyItemUtil.getReduceMoneyItem(PluginData.reduceMoney2.toString()));
	
	return gui;
}
}
