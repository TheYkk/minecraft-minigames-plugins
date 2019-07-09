package net.theykk.parkur;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{

	
	public String prefix = ChatColor.RED+"["+ChatColor.BLUE+"Parkur"+ChatColor.RED+"] "+ChatColor.RESET; 
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Listen(this), this);

	}

}
