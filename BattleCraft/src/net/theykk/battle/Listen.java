package net.theykk.battle;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Listen implements Listener {

	
	
	@EventHandler
	public void oldur(EntityDeathEvent e) {
		Entity killer = e.getEntity().getKiller();
		Entity olen = e.getEntity();
		
		if(killer instanceof Player && olen instanceof Zombie || olen instanceof Skeleton || olen instanceof Spider) {
			Player player = (Player) killer;
			
		}
		
	}

	@EventHandler
	public void sign(SignChangeEvent e)  {
	  Player p = e.getPlayer();
	 if ((e.getLine(0).equalsIgnoreCase("[parkur]"))) {
	        if (e.getLine(1).equalsIgnoreCase("katil")){
	          if (e.getLine(2) != null){
					p.sendMessage(this.plugin.prefix+"Tabela Degisti!");
					String arena = e.getLine(2);
					e.setLine(0, ChatColor.BOLD +this.plugin.prefix );
		            e.setLine(1, ChatColor.DARK_GRAY + "Parkur'a Gir");
		            e.setLine(2, ChatColor.UNDERLINE + arena);
		            e.setLine(3, ChatColor.GREEN + "0/12" );
	          }else {p.sendMessage(ChatColor.RED + "Parkur Ýsmi Hatalý");}
	        }else { p.sendMessage(ChatColor.RED + "2. Satýr Hatalý!");}
	    }
	}
	
	public void add (final Player player) {
		final ScoreboardManager manager = Bukkit.getScoreboardManager();
		final Scoreboard board = manager.getNewScoreboard();
		
		Objective objective = board.registerNewObjective("Scoreboard","Katiller");
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score spacer =objective.getScore("&d");
		spacer.setScore(2);
		
		Score score =objective.getScore(ChatColor.WHITE+"Alt Liste");
		score.setScore(1);
	
	}
	 @EventHandler
	 public void gelen (PlayerJoinEvent e) {
		 Player p = e.getPlayer();
		 this.add(p);
	 }
	
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent e) {
        Block b = e.getBlockPlaced();
        ItemStack inh = e.getPlayer().getInventory().getItemInMainHand();
            if (b != null && inh != null) {
                if (b.getType() == Material.MOB_SPAWNER && inh.getType() == Material.MOB_SPAWNER) {
                    ItemMeta im = inh.getItemMeta();

                    if (im.getDisplayName().toString().equalsIgnoreCase("Zombie Spawner")) {
                        setSpawner(b, EntityType.ZOMBIE);
                    }else if (im.getDisplayName().toString().equalsIgnoreCase("skeleton Spawner")) {
                        setSpawner(b, EntityType.SKELETON);
                    }else{
                        e.getPlayer().sendMessage("Koyulan blok"+im.getDisplayName());
                    }
                }
            }

    }


    public void setSpawner(Block block, EntityType ent) {
        BlockState blockState = block.getState();
        CreatureSpawner spawner = ((CreatureSpawner) blockState);
        spawner.setSpawnedType(ent);
        spawner.setDelay(1);

        blockState.update();
    }



}
