package gui.qie.guitransaction;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import gui.qie.guitransaction.data.TransactionsData;
import gui.qie.guitransaction.data.guidata.GuiData;
import gui.qie.guitransaction.infoes.TransactionInfo;
import gui.qie.guitransaction.listeners.InventoryClickListener;
import gui.qie.guitransaction.listeners.InventoryCloseListener;
import gui.qie.guitransaction.utils.VaultUtil;

public class GuiTransaction extends JavaPlugin{
	public static Plugin plugin;
	public static Plugin getPlugin(){
		return plugin;
	}
	@Override
	public void onEnable(){
		plugin=this;
		
		if (!VaultUtil.initVault(this))
	    {
	      getLogger().severe("前置:Vault 加载失败!");
	      return;
	    }
	    getLogger().severe("前置:Vault 加载成功");
		getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
		
		getLogger().info("插件加载完毕");
	}
	@Override
	public void onDisable(){
            getLogger().info("插件已卸�?");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("tr")){
            if(sender instanceof Player){
            	Player player = (Player)sender;
            	if(args.length==0){
            		player.sendMessage("§e/tr ID �?请对方交�?");
            		player.sendMessage("§e/tr yes同意交易");
            	}else if(args.length==1){
            		
            		if(args[0].equalsIgnoreCase("yes")){
            			TransactionInfo info=TransactionsData.infoMap.get(player);
            			if(info!=null){
            				Player playerB=info.nextInfo.player;
            				Inventory playerGui=GuiData.INSTACNE.getTransactionGui(player,playerB);
            				Inventory playerBGui=GuiData.INSTACNE.getTransactionGui(playerB,player);
            				TransactionsData.infoMap.get(player).transactionGui=playerGui;
            				TransactionsData.infoMap.get(playerB).transactionGui=playerBGui;
            				player.openInventory(TransactionsData.infoMap.get(player).transactionGui);
            				playerB.openInventory(TransactionsData.infoMap.get(playerB).transactionGui);
            			}
            		}else{
            			String playerId=args[0];
						Player playerB=Bukkit.getPlayer(playerId);
						if(player.equals(playerB)){
							player.sendMessage("§c不能跟自己交�?!");
							return true;
						}
            			if(playerB!=null){
            				TransactionsData.infoMap.put(player, new TransactionInfo(player));//player鐨刬nfo
            				TransactionsData.infoMap.put(playerB, new TransactionInfo(playerB));
            				TransactionsData.infoMap.get(player).nextInfo=TransactionsData.infoMap.get(playerB);
            				TransactionsData.infoMap.get(playerB).nextInfo=TransactionsData.infoMap.get(player);
            				
            				player.sendMessage("§e已邀请玩家�?7: §f"+playerB.getName()+"§e参与交易,同意后会�?始交�?");
            				playerB.sendMessage("§f"+player.getName()+" §e�?请您进行交易,同意请使用指�?/tr yes");
            				
            			}else{
            				player.sendMessage("§c请正确输入玩家名�?");
            			}
            		}
            	}
            	return true;
            }
        }
        return false;
    }
}
