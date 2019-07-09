package net.theykk.ykkpls;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class kirdim  implements Listener {
	
	
//	Ana plm = new Ana();
	
//Ölünce gamemode 3 e al
@EventHandler(priority=EventPriority.LOW)
public void onPlayerDeath(PlayerDeathEvent event){
		event.setDeathMessage(null);
        Player player = event.getEntity();
        player.getLocation().getWorld().playEffect(player.getLocation(), Effect.SMOKE, 1);
        player.setGameMode(GameMode.SPECTATOR);
        Bukkit.broadcastMessage(ChatColor.YELLOW+"[UHC] "+ChatColor.LIGHT_PURPLE+ player.getDisplayName()+" Malesef Öldü. Allah Rahmet Eylesin");
        
}
//Giriþ yapýnca reklamýmý yap 
 @EventHandler
 public void onPlayerJoin(PlayerJoinEvent e){
	 	Player p = e.getPlayer();
	 	Bukkit.broadcastMessage(ChatColor.YELLOW+"[UHC] "+ChatColor.GOLD+ p.getName() + ChatColor.AQUA+" Sunucumuza Giriþ Yaptý."); 
	 	e.setJoinMessage(null);
 }
//Hazýr eþya gelmesi

	@EventHandler
public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		Block block = e.getBlock();
//Cam  kýrýlmasý olayý
		 if (block.getType() == Material.GLASS) {
			 if (p.getGameMode() != GameMode.CREATIVE) {
				 e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GLASS,1));
			 }
		 }
//Iþýk Taþý kýrýlmasý olayý
		 if (block.getType() == Material.GLOWSTONE) {
			 if (p.getGameMode() != GameMode.CREATIVE) {
				 e.setCancelled(true);   
				 e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GLOWSTONE,1));
				 block.setType(Material.AIR);
			 }
		 }
		 
		 if (block.getType() == Material.IRON_ORE) {
			 if (p.getGameMode() != GameMode.CREATIVE) {
				 e.setCancelled(true);   
				 e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT,1));
				 ExperienceOrb orb = block.getWorld().spawn(block.getLocation().add(0.5, 0.5, 0.5), ExperienceOrb.class);
				 orb.setExperience(1);
				 
				 block.setType(Material.AIR);
			 }
		 }
		 
		 if (block.getType() == Material.GOLD_ORE) {
			 if (p.getGameMode() != GameMode.CREATIVE) {
				 e.setCancelled(true);   
				 e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT,1));
				 ExperienceOrb orb = block.getWorld().spawn(block.getLocation().add(0.5, 0.5, 0.5), ExperienceOrb.class);
				 orb.setExperience(1);
				 
				 block.setType(Material.AIR);
			 }
		 }	
	}

//Aðaç kýrma
private LinkedList<Location> blocks = new LinkedList<Location>();
private int logsFound = 0;
private int leavesFound = 0;
  
	  @EventHandler
public void onTreeBlockDestroyed(BlockBreakEvent event)
	  {
	    Block block = event.getBlock();
	    if ((block.getType() == Material.LOG) || (block.getType() == Material.LOG_2)) {
	      destroyTree(event.getPlayer(), block.getLocation());
	    }
	  }
	  
public synchronized void destroyTree(Player player, Location l)
	  {
	    getSurroundingLogs(l);
	    if ((this.logsFound >= 3) && (this.leavesFound > 0))
	    {
	      int highestLogY = 0;
	      for (Location loc : this.blocks)
	      {
	        if (loc.getBlockY() > highestLogY) {
	          highestLogY = loc.getBlockY();
	        }
	        Block block = loc.getBlock();
	        Material blockMat = block.getType();
	        if (isLog(blockMat))
	        {
	          ItemStack stack = new ItemStack(blockMat, 1);
	          
	          player.getInventory().addItem(new ItemStack[] { stack});
	          block.setType(Material.AIR);
	        }
	      }
	      ItemStack apple = new ItemStack(Material.APPLE, 1);
	      player.getInventory().addItem(apple);
	      this.blocks.clear();
	      
	      clearLeaves(l.getWorld(), l.getBlockX(), highestLogY, l.getBlockZ());
	    }
	    this.logsFound = 0;
	    this.leavesFound = 0;
	  }
	  
private void getSurroundingLogs(Location loc)
	  {
	    World world = loc.getWorld();
	    
	    int x = loc.getBlockX();
	    int y = loc.getBlockY();
	    int z = loc.getBlockZ();
	    for (int x1 = x - 1; x1 <= x + 1; x1++) {
	      for (int y1 = y - 1; y1 <= y + 1; y1++) {
	        for (int z1 = z - 1; z1 <= z + 1; z1++)
	        {
	          Block block = world.getBlockAt(x1, y1, z1);
	          Material blockMat = block.getType();
	          Location blockLoc = block.getLocation();
	          if ((isLog(blockMat)) || (isLeaf(blockMat))) {
	            if (!this.blocks.contains(blockLoc)) {
	              if (isLog(blockMat))
	              {
	                this.blocks.add(blockLoc);
	                getSurroundingLogs(blockLoc);
	                this.logsFound += 1;
	              }
	              else
	              {
	                this.leavesFound += 1;
	              }
	            }
	          }
	        }
	      }
	    }
	  }
	  
private void clearLeaves(World world, int x, int y, int z)
	  {
	    for (int x1 = x - 4; x1 <= x + 4; x1++)
	    {
	      int dx = Math.abs(x - x1);
	      for (int y1 = y - 4; y1 <= y + 4; y1++)
	      {
	        int dy = Math.abs(y - y1);
	        for (int z1 = z - 4; z1 <= z + 4; z1++)
	        {
	          int dz = Math.abs(z - z1);
	          double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
	          if (distance < 4.0D)
	          {
	            Block b = world.getBlockAt(x1, y1, z1);
	            if (isLeaf(b.getType())) {
	              b.setType(Material.AIR);
	            }
	          }
	        }
	      }
	    }
	  }
	  
public boolean isLog(Material m)
  {
    return (m == Material.LOG) || (m == Material.LOG_2);
  }
  
public boolean isLeaf(Material m)
  {
    return (m == Material.LEAVES) || (m == Material.LEAVES_2);
  }

	  
	  //World Border yapma
public void borderyap(){
          for(World world : Bukkit.getWorlds()){
        	  	world.setDifficulty(Difficulty.HARD);
                  WorldBorder wb = world.getWorldBorder();
                  wb.setCenter(0, 0);
                  wb.setSize(1000);
                  wb.setSize(20, 30);
                  wb.setDamageAmount(0.1);
                  wb.setDamageBuffer(1);
                  wb.setWarningDistance(2);
                  wb.setWarningTime(5);
                  //Bukkit.broadcastMessage("Le worldborder du monde " + world.getName() + " à bien été défini !");
          }
  }


}
