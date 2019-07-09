package net.theykk.ykkpls;



import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
/*
 * Plugin Yazarý: Yusuf Kaan Karakaya (TheYkk)
 * Tarih 30.07.2017
 * Amaç: Uhc Survival server plugini
 */
public class Ana extends JavaPlugin{
//	kirdim clsm = new kirdim();
	
	public static Plugin plugin;
	@Override
	public void onEnable() {
		getLogger().info("[Ykkpl] V1.0  Aktif oldu.");
		getServer().getPluginManager().registerEvents(new kirdim(), this);
		//clsm.borderyap();
		//Geri Sayaç
		
	
	}
	
	@Override
	public void onDisable() {
		getLogger().warning("[Ykkpl] V1.0 pasif oldu.");
	}
	
	int PlayersOnline = getServer().getOnlinePlayers().size();
	
	public void gerisay() {
		if (PlayersOnline > 8) {
			for(Player all:getServer().getOnlinePlayers()){
		         new Geri(this, all, 20);
			}
		}else if(PlayersOnline > 6) {
			for(Player all:getServer().getOnlinePlayers()){
		         new Geri(this, all, 30);
			}
		}else {
			for(Player all:getServer().getOnlinePlayers()){
		         new Geri(this, all, 100);
			}
		}
	}
}
