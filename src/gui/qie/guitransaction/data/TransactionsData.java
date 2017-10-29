package gui.qie.guitransaction.data;

import java.util.HashMap;

import org.bukkit.entity.Player;

import gui.qie.guitransaction.infoes.TransactionInfo;

public class TransactionsData {
public static HashMap<Player,TransactionInfo> infoMap;
static{
	infoMap=new HashMap<>();
}
}
