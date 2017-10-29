package gui.qie.guitransaction.data.guidata;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiItemData {
	/**
	 * ��ɫ������
	 */
	private static ItemStack blackGlass=new ItemStack(Material.GLASS);
@SuppressWarnings("unused")
private Skull playerSkull;

public static final GuiItemData INSTACNE=new GuiItemData();

public static ItemStack getBlackGlass(){
	return blackGlass;
}
/**
 * �õ�ȷ�����׵���Ʒ(���κβ���)
 */
public static ItemStack getFirmTransactionItem(){//ȷ������ ��ť
	ItemStack item=new ItemStack(146);
	ItemMeta itemMeta=item.getItemMeta();
	itemMeta.setDisplayName("��eȷ������");
	List<String> lore=new ArrayList<String>();
	lore.add("��a����˰�ťȷ������");
	itemMeta.setLore(lore);
	item.setItemMeta(itemMeta);
	return item;
}

public static ItemStack getUpdataItem(){//������Ʒ ��ť
	ItemStack item=new ItemStack(Material.MILK_BUCKET);
	ItemMeta itemMeta=item.getItemMeta();
	itemMeta.setDisplayName("��a���½�����Ʒ");
	List<String> lore=new ArrayList<String>();
	lore.add("��a����˰�ť���½�����Ʒ(������п��ܵ�����Ʒ����ȷ)");
	itemMeta.setLore(lore);
	item.setItemMeta(itemMeta);
	return item;
}
}
