package me.supermaxman.xenpc.listeners;

import me.supermaxman.xenpc.main.XeNPC;
import me.supermaxman.xenpc.objects.XeNPCBase;
import me.supermaxman.xenpc.objects.XeNPCHuman;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitScheduler;

public class XeNPCListener implements Listener {
    final XeNPC plugin;

    public XeNPCListener(XeNPC plugin) {
        this.plugin = plugin;
    }
    BukkitScheduler scheduler = Bukkit.getScheduler();
    
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
    	
    	net.minecraft.server.Entity mcEntity = ((CraftEntity) event.getRightClicked()).getHandle();
    	if(mcEntity instanceof XeNPCBase){
    		XeNPCHuman npc = ((XeNPCBase) mcEntity).getNPC();
    		Player p = event.getPlayer();
    		p.openInventory(npc.getInventory());
    	}
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
    	net.minecraft.server.Entity mcEntity = ((CraftEntity) event.getEntity()).getHandle();
    	if(mcEntity instanceof XeNPCBase&&event.isCancelled()!=true){
    		event.setCancelled(true);
    		XeNPCHuman npc = ((XeNPCBase) mcEntity).getNPC();
    		npc.damage(event.getDamage());
    		
    	}
    }
    
}
