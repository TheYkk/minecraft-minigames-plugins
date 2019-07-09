package net.theykk.UhcSW;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Listen implements Listener {

	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
			Player p = e.getPlayer();
			
			Block block = e.getBlock();

			 if (block.getType() == Material.YELLOW_FLOWER || block.getType() == Material.RED_ROSE || block.getType() == Material.getMaterial(38)  ) {
				 if (p.getGameMode() != GameMode.CREATIVE) {
					 Random rn = new Random();
					 int r5 = rn.nextInt(6);
					 int r10 = rn.nextInt(11);
					 int r20 = rn.nextInt(21);
					 int r1 = rn.nextInt(2);
					 int r64 = rn.nextInt(65);
					 
					 ItemStack item = new ItemStack(Material.GOLDEN_APPLE,r5);
					 ItemStack item2 = new ItemStack(Material.WOOD_PLATE,r64);
					 ItemStack item3 = new ItemStack(Material.DIAMOND,r5);
					 
					 
					 e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
				 }
			 }
	
		}
}
