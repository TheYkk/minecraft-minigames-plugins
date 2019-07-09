package net.theykk.battle;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Komut implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String label,String args[]) {
		
		 if (!(sender instanceof Player)) {
             sender.sendMessage(Main.getPlugin(Main.class).prefix+ChatColor.RED + "Sadece kullanýcýlar kullanabilir.");
             return true;
		 }
		 Player p = (Player) sender;
         
         if (cmd.getName().equalsIgnoreCase("spal")) {
        	 
			if(args.length==1) {				
                 try {
                	 	String name = args[0];
                         EntityType type = EntityType.valueOf(args[0].toUpperCase());
    /*	
                         ItemStack localItemStack = new ItemStack(Material.MOB_SPAWNER, 1, type.getTypeId());
                         ItemMeta localItemMeta = localItemStack.getItemMeta();
                         localItemMeta.setDisplayName("§a" + type.getName() + " Spawner");

                         localItemStack.setItemMeta(localItemMeta);
                      
                         p.getInventory().addItem(localItemStack);*/
                         
                         p.sendMessage(Main.getPlugin(Main.class).prefix + "§a"+name+" Spawner");
                         
                 }
                 catch (Exception e) {
                         p.sendMessage(Main.getPlugin(Main.class).prefix+ChatColor.RED + "Böyle Bir Yaratýk Yok!");
                         return true;
                 }
                 
			}else {
				p.sendMessage(Main.getPlugin(Main.class).prefix+ChatColor.RED + "Mob tipi seçiniz");
                return true;
			}
         }else {
        	 
        	 p.sendMessage("olmadý");
         }
         
         
         return true;
	}
}
