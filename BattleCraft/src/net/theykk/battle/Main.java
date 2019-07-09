package net.theykk.battle;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;


public class Main extends JavaPlugin  {

	public String prefix = ChatColor.RED+"["+ChatColor.GOLD+"BattleCraft"+ChatColor.RED+"] "+ChatColor.WHITE; 
	public void onEnable() {
		getLogger().info(" V1.0  Aktif oldu.");
		getServer().getPluginManager().registerEvents(new Listen(), this);
		this.getConfig().addDefault("mobdeather", 0);
	}
	
	@Override
	public void onDisable() {
		getLogger().warning(" V1.0 pasif oldu.");
	}
	
	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String label,String args[]) {
		
		

		 Player p = (Player) sender;
         if (cmd.getName().equalsIgnoreCase("spal") && sender instanceof Player) {
        	 
			if(args.length==1) {
                 try {
                	 	String name = args[0];
                         EntityType type = EntityType.valueOf(args[0].toUpperCase());
                         ItemStack localItemStack = new ItemStack(Material.MOB_SPAWNER, 1);
                         ItemMeta localItemMeta = localItemStack.getItemMeta();
                         localItemMeta.setDisplayName(name + " Spawner");
                       //  localItemMeta.setLore(Arrays.asList(new String[]{"SpawnCount: 12", "RequiredPlayerRange: 15","SpawnRange: 15"}));
                         localItemStack.setItemMeta(localItemMeta);
					 p.sendMessage(String.valueOf(localItemMeta.getLore()));
                         p.getInventory().addItem(localItemStack);

                         p.sendMessage(prefix + ChatColor.YELLOW+name+" Spawner");

                 }
                 catch (Exception e) {
                         p.sendMessage(prefix+ChatColor.RED + "Böyle Bir Yaratýk Yok!");
                         return true;
                 }

			}else {
				p.sendMessage(prefix+ChatColor.RED + "Mob tipi seçiniz");
                return true;
			}
         }else {
        	 
        	 p.sendMessage("olmadý");
         }
         
         
         return true;
	}



}
