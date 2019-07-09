package net.theykk.parkur;



import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
//import org.bukkit.DyeColor;
//import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventHandler;

public class Listen implements Listener {
	
		private Main plugin;
	  
	  public Listen(Main listen)
	  {
	    this.plugin = listen;
	  }
/*
	  public void gelen(Player p) {
		 // this.playerList.put(p.getUniqueId());
		  
	  }*/
	  
	  /*
	  @EventHandler
	  public void yur(PlayerMoveEvent e) {
		  Player p = e.getPlayer();
	//	  Material to = e.getTo().getBlock().getType();
		  //Colorable cl = ((Colorable) e.getTo().getBlock().getState().getData());
		 // if(cl.getColor()==DyeColor.WHITE) {
			 
	//	  }
		  /*if(to.equals(Material.STAINED_GLASS,Dy)) {
			  
		  }*//*
			  
		  p.sendMessage(this.plugin.prefix+"Bastýnmý?"+p.getLocation().getBlock().getType());
			  if(e.getTo().getBlock().getType()==Material.STAINED_GLASS){
				  p.sendMessage(this.plugin.prefix+"Bastýn");
				  Colorable cl = ((Colorable) e.getTo().getBlock().getState().getData());
				    DyeColor color = cl.getColor();
				    if(color==DyeColor.WHITE) {
				    	 p.sendMessage(this.plugin.prefix+"Bastýn");
				    }
				}

	  }
*/int i = 0;
	@EventHandler
	    public void onPlayerMove(PlayerMoveEvent e) {
	        Block toBlock = e.getTo().getBlock().getRelative(BlockFace.DOWN); // Get the block under the block that the player walks to
	        Material to = toBlock.getType(); // Get the material of the block
	        if(to.equals(Material.GLASS)) { // Check if the block if either GLASS or STAINEDGLASS
				toBlock.setTypeId(95); // If not, set the target's block to block ID 95
	        	
				Location lc = toBlock.getLocation();

			
				
				 Player p = e.getPlayer();

				 
				if(i==0) {
					 int x = lc.getBlockX()-2;
			         int y = lc.getBlockY()+1;
			         int z = lc.getBlockZ()+2;
			         i++;
			         Block tom = p.getWorld().getBlockAt(x,y,z);
			         tom.setType(Material.GLASS);
				}else if(i==1) {
					 int x = lc.getBlockX()-2;
			         int y = lc.getBlockY()+1;
			         int z = lc.getBlockZ()-2;
					 i++;
					 Block tom = p.getWorld().getBlockAt(x,y,z);
					 tom.setType(Material.GLASS);
				}else if(i==2) {
					int x = lc.getBlockX()+1;
			         int y = lc.getBlockY();
			         int z = lc.getBlockZ()-2;
			         i++;
			         Block tom = p.getWorld().getBlockAt(x,y,z);
			         tom.setType(Material.GLASS);
				}else if(i==3){
					int x = lc.getBlockX()+2;
			         int y = lc.getBlockY()+1;
			         int z = lc.getBlockZ();
			         i++;
			         Block tom = p.getWorld().getBlockAt(x,y,z);
			         tom.setType(Material.GLASS);
				}else if(i==4){
					int x = lc.getBlockX()+2;
			         int y = lc.getBlockY()+1;
			         int z = lc.getBlockZ()-2;
			         i=0;
			         Block tom = p.getWorld().getBlockAt(x,y,z);
			         tom.setType(Material.GLASS);
				}
				
				 
				 
				
	        }
		}
}