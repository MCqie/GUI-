package gui.qie.guitransaction.utils;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class VaultUtil {
	private static Economy economy = new Economy() {
		
		@Override
		public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse withdrawPlayer(String arg0, double arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public EconomyResponse isBankOwner(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse isBankMember(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean hasBankSupport() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasAccount(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean hasAccount(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean has(String arg0, String arg1, double arg2) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean has(String arg0, double arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public List<String> getBanks() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public double getBalance(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public double getBalance(String arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public int fractionalDigits() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String format(double arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse depositPlayer(String arg0, double arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse deleteBank(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String currencyNameSingular() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String currencyNamePlural() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean createPlayerAccount(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean createPlayerAccount(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public EconomyResponse createBank(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse bankWithdraw(String arg0, double arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse bankHas(String arg0, double arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse bankDeposit(String arg0, double arg1) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public EconomyResponse bankBalance(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	public static boolean hasMoney(Player player,double money){
		return economy.has(player.getName(), money);
	}
	  public static boolean initVault(Plugin plugin)
	  {
	    boolean hasNull = false;
	    RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
	    if ((economyProvider != null) && 
	      ((economy = (Economy)economyProvider.getProvider()) == null)) {
	      hasNull = true;
	    }
	    return !hasNull;
	  }
	  
	  public static Economy getEconomy()
	  {
	    return economy;
	  }
}
